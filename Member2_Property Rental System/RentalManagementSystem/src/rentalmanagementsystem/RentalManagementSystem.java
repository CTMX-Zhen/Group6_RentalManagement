/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmanagementsystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import rentalmanagementsystem.SplayTree;
import rentalmanagementsystem.Rental;
import rentalmanagementsystem.CSVLoader;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class RentalManagementSystem {

    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        CSVLoader loader = new CSVLoader();

        List<Rental> rentals = loader.loadFromCSV();
        for (Rental r : rentals) {
            tree.insert(r);
        }

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Property Rental Management ---");
            System.out.println("1. Display All Properties");
            System.out.println("2. Search Property by ID");
            System.out.println("3. Add New Property");
            System.out.println("4. Delete Property");
            System.out.println("5. Update Property");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    tree.display();
                    break;
                case 2:
                    System.out.print("Enter Property ID: ");
                    long id = sc.nextLong();
                    Rental found = tree.search(id);
                    System.out.println(found != null ? "Found: " + found : "Not Found");
                    break;
                case 3:
                    Rental newRental = inputRental(sc);
                    tree.insert(newRental);
                    saveToCSV(tree, "src/csv/mudah-apartment-kl-selangor.csv");
                    System.out.println("Property added successfully!");
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    long delId = sc.nextLong();
                    tree.delete(delId);
                    saveToCSV(tree, "src/csv/mudah-apartment-kl-selangor.csv");
                    break;
                case 5:
                    System.out.print("Enter ID to update: ");
                    long updId = sc.nextLong();
                    sc.nextLine();
                    Rental existing = tree.search(updId);
                    if (existing == null) {
                        System.out.println("Property not found!");
                    } else {
                        Rental updatedRental = inputRentalWithId(sc, updId, existing);
                        tree.update(updId, updatedRental);
                        saveToCSV(tree, "src/csv/mudah-apartment-kl-selangor.csv");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }

    private static Rental inputRental(Scanner sc) {
        System.out.print("Enter ID: ");
        long id = sc.nextLong();
        sc.nextLine();
        return inputRentalWithId(sc, id);
    }

    private static Rental inputRentalWithId(Scanner sc, long id) {
        System.out.print("Enter Property Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Completion Year: ");
        String year = sc.nextLine();
        System.out.print("Enter Monthly Rent: ");
        double rent = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Location: ");
        String loc = sc.nextLine();
        System.out.print("Enter Property Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Rooms: ");
        String rooms = sc.nextLine();
        System.out.print("Enter Parking: ");
        String parking = sc.nextLine();
        System.out.print("Enter Bathroom: ");
        String bathroom = sc.nextLine();
        System.out.print("Enter Size: ");
        String size = sc.nextLine();
        System.out.print("Enter Furnished: ");
        String furnished = sc.nextLine();

        return new Rental(id, name, year, rent, loc, type, rooms, parking, bathroom, size, furnished);
    }
    
    //For update function 
    private static Rental inputRentalWithId(Scanner sc, long id, Rental existing) {
        System.out.println("Leave blank to keep current value.");

        System.out.print("Enter Property Name (" + existing.getPropName() + "): ");
        String name = sc.nextLine();
        if (name.isEmpty()) {
            name = existing.getPropName();
        }

        System.out.print("Enter Completion Year (" + existing.getCompletionYear() + "): ");
        String year = sc.nextLine();
        if (year.isEmpty()) {
            year = existing.getCompletionYear();
        }

        System.out.print("Enter Monthly Rent (" + existing.getMonthlyRent() + "): ");
        String rentInput = sc.nextLine();
        double rent = rentInput.isEmpty() ? existing.getMonthlyRent() : Double.parseDouble(rentInput);

        System.out.print("Enter Location (" + existing.getLocation() + "): ");
        String loc = sc.nextLine();
        if (loc.isEmpty()) {
            loc = existing.getLocation();
        }

        System.out.print("Enter Property Type (" + existing.getPropertyType() + "): ");
        String type = sc.nextLine();
        if (type.isEmpty()) {
            type = existing.getPropertyType();
        }

        System.out.print("Enter Rooms (" + existing.getRooms() + "): ");
        String rooms = sc.nextLine();
        if (rooms.isEmpty()) {
            rooms = existing.getRooms();
        }

        System.out.print("Enter Parking (" + existing.getParking() + "): ");
        String parking = sc.nextLine();
        if (parking.isEmpty()) {
            parking = existing.getParking();
        }

        System.out.print("Enter Bathroom (" + existing.getBathroom() + "): ");
        String bathroom = sc.nextLine();
        if (bathroom.isEmpty()) {
            bathroom = existing.getBathroom();
        }

        System.out.print("Enter Size (" + existing.getSize() + "): ");
        String size = sc.nextLine();
        if (size.isEmpty()) {
            size = existing.getSize();
        }

        System.out.print("Enter Furnished (" + existing.getFurnished() + "): ");
        String furnished = sc.nextLine();
        if (furnished.isEmpty()) {
            furnished = existing.getFurnished();
        }

        return new Rental(id, name, year, rent, loc, type, rooms, parking, bathroom, size, furnished);
    }

    private static void saveToCSV(SplayTree tree, String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("ads_id,prop_name,completion_year,monthly_rent,location,property_type,rooms,parking,bathroom,size,furnished");
            saveNode(tree.getRoot(), pw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveNode(SplayNode node, PrintWriter pw) {
        if (node != null) {
            saveNode(node.left, pw);
            Rental r = node.rental;
            pw.printf("%d,%s,%s,%.2f,%s,%s,%s,%s,%s,%s,%s%n",
                    r.getId(), r.getPropName(), r.getCompletionYear(), r.getMonthlyRent(),
                    r.getLocation(), r.getPropertyType(), r.getRooms(), r.getParking(),
                    r.getBathroom(), r.getSize(), r.getFurnished());
            saveNode(node.right, pw);
        }
    }
}
