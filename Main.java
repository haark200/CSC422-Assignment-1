import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
    Scanner inputObj = new Scanner(System.in);

    //Path to find animal list
    Path petsFilePath = Paths.get("pets.txt");

    //If the pets file does not exist
    if(!Files.exists(petsFilePath)) {
      Files.createFile(petsFilePath);
    }

    //Empty ArrayList
    ArrayList<Pet> pets = new ArrayList<Pet>();

    ListIterator<String> fileIterator = Files.readAllLines(petsFilePath).listIterator();

    while(fileIterator.hasNext()) {
      //If list is max capacity
      if(pets.size() >= 5) {
        System.out.println("Pet capacity reached.");

        break;
      }

      String petEntry = fileIterator.next();

      try {
        
        int delim = petEntry.indexOf(" ");

        String petName = petEntry.substring(0, delim);
          
        int petAge = Integer.parseInt(petEntry.substring(delim + 1));

        //Age is smaller than 1 or larger than 20
        if(petAge < 1 || petAge > 20) {
          System.out.println("Invalid age");
        }
        //No name input
        else if(petName.length() == 0) {
          System.out.println("Invalid name");
        }
        else {
          pets.add(new Pet(petName, petAge));  
        }
      }
      // catch for invalid age
      catch(NumberFormatException exception) {
        System.out.println("Invalid age");
      }
      // catch for absence of delimiter (i.e., " ")
      catch(IndexOutOfBoundsException exception) {
        System.out.println("Error");
      }
    }

    /* Constructors for Pet traits */
    class Pet {
        private String name;
        private int age;
        public Pet(String name, int age) 
        {
            this.name = name;
            this.age = age;
        }
        public String getName() 
        {
            return name;
        }
        public int getAge() 
        {
            return age;
        }
        public void setName(String name) 
        {
            this.name = name;
        }
        public void setAge(int age) 
        {
            this.age = age;
        }
    }

    /* Database class */
    public class PetDatabase {
        private ArrayList<Pet> pets;
        public PetDatabase() 
        {
            pets = new ArrayList<>();
        }
        /* Add pet to database */
        public void addPet(String name, int age) {
            pets.add(new Pet(name, age));
            System.out.println("Pet has been added");
        }
        /* Print out all pets in database */
        public void showPets() {
            System.out.println("+-------------------------+");
            System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
            System.out.println("+-------------------------+");
            for (int i = 0; i < pets.size(); i++) {
                Pet pet = pets.get(i);
                System.out.printf("| %-3d | %-10s | %-4d |\n", i, pet.getName(), pet.getAge());
            }
            System.out.println("+-------------------------+");
            System.out.println(pets.size() + " rows in set.");
        }
        /* Search by name, print out results */
        public void searchPetsByName(String nameSearch) {
            System.out.println("+-------------------------+");
            System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
            System.out.println("+-------------------------+");
            for (int i = 0; i < pets.size(); i++) {
                Pet pet = pets.get(i);
                if (pet.getName().equalsIgnoreCase(nameSearch)) {
                    System.out.printf("| %-3d | %-10s | %-4d |\n", i, pet.getName(), pet.getAge());
                }
            }
            System.out.println("+-------------------------+");
        }
        /* Search by age, print out results */
        public void searchPetsByAge(int ageSearch) {
            System.out.println("+-------------------------+");
            System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
            System.out.println("+-------------------------+");
            for (int i = 0; i < pets.size(); i++) {
                Pet pet = pets.get(i);
                if (pet.getAge() == ageSearch) {
                    System.out.printf("| %-3d | %-10s | %-4d |\n", i, pet.getName(), pet.getAge());
                }
            }
            System.out.println("+-------------------------+");
        }
        /* Update pet by searching for ID */
        public void updatePet(int id, String newName, int newAge) {
            if (id >= 0 && id < pets.size()) {
                Pet pet = pets.get(id);
                pet.setName(newName);
                pet.setAge(newAge);
                System.out.println("Pet has been updated");
            } else {
                System.out.println("Invalid ID, please retry.");
            }
        }
        /* Remove pet by searching for ID */
        public void removePet(int id) {
            if (id >= 0 && id < pets.size()) {
                pets.remove(id);
                System.out.println("Pet has been removed.");
            } else {
                System.out.println("Invalid ID, please retry.");
            }
        }

        /* Interface for database */
        public void main(String[] args) {
            PetDatabase database = new PetDatabase();
            Scanner scnr = new Scanner(System.in);
            while (true) {
                System.out.println("Options:");
                System.out.println("1. Add a pet");
                System.out.println("2. Show all pets");
                System.out.println("3. Search pets by name");
                System.out.println("4. Search pets by age");
                System.out.println("5. Update pet");
                System.out.println("6. Remove pet");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scnr.nextInt();
                scnr.nextLine(); 
                /* Cases for our choices */
                switch (choice) {
                    case 1:
                        System.out.print("Enter pet name: ");
                        String name = scnr.nextLine();
                        System.out.print("Enter pet age: ");
                        int age = scnr.nextInt();
                        database.addPet(name, age);
                        break;
                    case 2:
                        database.showPets();
                        break;  
                    case 3:
                        System.out.print("Enter name of pet you'd like to search for: ");
                        String nameSearch = scnr.nextLine();
                        database.searchPetsByName(nameSearch);
                        break;
                    case 4:
                        System.out.print("Enter age of pet you'd like to search for: ");
                        int ageSearch = scnr.nextInt();
                        database.searchPetsByAge(ageSearch);
                        break; 
                    case 5:
                        System.out.print("Enter the ID of the pet to update: ");
                        int idToUpdate = scnr.nextInt();
                        scnr.nextLine();
                        System.out.print("Enter new name: ");
                        String newName = scnr.nextLine();
                        System.out.print("Enter new age: ");
                        int newAge = scnr.nextInt();
                        database.updatePet(idToUpdate, newName, newAge);
                        break;
                    case 6:
                        System.out.print("Enter the ID of the pet to remove: ");
                        int idToRemove = scnr.nextInt();
                        database.removePet(idToRemove);
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        scnr.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option, please retry.");
                        break;
                    
                }
            }
        }
    }
}