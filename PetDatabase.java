import java.util.ArrayList;
import java.util.Scanner;

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
    
    /* Interface for database */
    public static void main(String[] args) {
        PetDatabase database = new PetDatabase();
        Scanner scnr = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            System.out.println("1. Add a pet");
            System.out.println("2. Show all pets");
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
            }
        }
    }
}
