/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class PropertyRentalSystem {

    private static final String CSV_PATH = "src/csv/mudah-apartment-kl-selangor.csv";

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        RentalService service = new RentalService();

        try {
            CsvLoader loader = new CsvLoader(CSV_PATH);
            List<Property> props = loader.load();
            service.addAll(props);
        } catch (Exception e) {
            System.out.println("Failed to load CSV: " + e.getMessage());
            e.printStackTrace();
        }

        int choice;
        do {
            printMenu();
            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: 
                    printList(service.listAll());
                    break;

                case 2: { 
                    long id = readLong("Enter ads_id to search: ");
                    Property p = service.get(id);
                    System.out.println(p == null ? "Not found." : p.toString());
                    break;
                }

                case 3: { 
                    long id = readLong("ads_id to delete: ");
                    boolean ok = service.delete(id);
                    System.out.println(ok ? "Deleted." : "Delete failed.");
                    break;
                }

                case 4: {
                    Property newP = promptNewProperty();
                    boolean added = service.add(newP);
                    System.out.println(added ? "Added." : "ads_id already exists.");
                    break;
                }

                case 5: {
                    long id = readLong("ads_id to update: ");
                    Property existing = service.get(id);
                    if (existing == null) {
                        System.out.println("Not found.");
                    } else {
                        Property upd = promptUpdate(existing);
                        boolean ok = service.update(id, upd);
                        System.out.println(ok ? "Updated." : "Update failed.");
                    }
                    break;
                }

                case 6:
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Property Rental System ---");
        System.out.println("1. List all properties (by ads_id)");
        System.out.println("2. Search property by ads_id");
        System.out.println("3. Delete a property");
        System.out.println("4. Add new property");
        System.out.println("5. Update property");
        System.out.println("6. Exit");
    }

    private static void printList(List<Property> props) {
        if (props == null || props.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (int i = 0; i < props.size(); i++) {
            Property p = props.get(i);
            System.out.println(p == null ? "(null)" : p.toString());
        }
        System.out.println("Total: " + props.size());
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); } catch (Exception e) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    private static long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try { return Long.parseLong(s); } catch (Exception e) {
                System.out.println("Enter a valid long.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try { return Double.parseDouble(s); } catch (Exception e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private static double parseDoubleSafe(String s, double def) {
        try { return Double.parseDouble(s); } catch (Exception e) { return def; }
    }

    private static Property promptNewProperty() {
        long id = readLong("ads_id: ");
        System.out.print("name: "); String name = sc.nextLine().trim();
        System.out.print("completion year (blank allowed): "); String year = sc.nextLine().trim();
        double rent = readDouble("monthly rent (RM): ");
        System.out.print("location: "); String loc = sc.nextLine().trim();
        System.out.print("property type: "); String type = sc.nextLine().trim();
        System.out.print("rooms: "); String rooms = sc.nextLine().trim();
        System.out.print("parking: "); String parking = sc.nextLine().trim();
        System.out.print("bathroom: "); String bathroom = sc.nextLine().trim();
        System.out.print("size (e.g., 850 sq.ft.): "); String size = sc.nextLine().trim();
        System.out.print("furnished: "); String furnished = sc.nextLine().trim();
        System.out.print("facilities: "); String fac = sc.nextLine().trim();
        System.out.print("additional facilities: "); String af = sc.nextLine().trim();
        System.out.print("region: "); String region = sc.nextLine().trim();

        return new Property(id, name, year, rent, loc, type, rooms, parking, bathroom, size,
                furnished, fac, af, region);
    }

    private static Property promptUpdate(Property existing) {
        System.out.println("Leave blank to keep current value.");

        System.out.print("name (" + existing.getPropName() + "): ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) name = existing.getPropName();

        System.out.print("completion year (" + existing.getCompletionYear() + "): ");
        String year = sc.nextLine().trim();
        if (year.isEmpty()) year = existing.getCompletionYear();

        System.out.print("monthly rent (" + existing.getMonthlyRent() + "): ");
        String sRent = sc.nextLine().trim();
        double rent = sRent.isEmpty() ? existing.getMonthlyRent() : parseDoubleSafe(sRent, existing.getMonthlyRent());

        System.out.print("location (" + existing.getLocation() + "): ");
        String loc = sc.nextLine().trim();
        if (loc.isEmpty()) loc = existing.getLocation();

        System.out.print("property type (" + existing.getPropertyType() + "): ");
        String type = sc.nextLine().trim();
        if (type.isEmpty()) type = existing.getPropertyType();

        System.out.print("rooms (" + existing.getRooms() + "): ");
        String rooms = sc.nextLine().trim();
        if (rooms.isEmpty()) rooms = existing.getRooms();

        System.out.print("parking (" + existing.getParking() + "): ");
        String parking = sc.nextLine().trim();
        if (parking.isEmpty()) parking = existing.getParking();

        System.out.print("bathroom (" + existing.getBathroom() + "): ");
        String bath = sc.nextLine().trim();
        if (bath.isEmpty()) bath = existing.getBathroom();

        System.out.print("size (" + existing.getSize() + "): ");
        String size = sc.nextLine().trim();
        if (size.isEmpty()) size = existing.getSize();

        System.out.print("furnished (" + existing.getFurnished() + "): ");
        String furnished = sc.nextLine().trim();
        if (furnished.isEmpty()) furnished = existing.getFurnished();

        System.out.print("facilities (" + existing.getFacilities() + "): ");
        String fac = sc.nextLine().trim();
        if (fac.isEmpty()) fac = existing.getFacilities();

        System.out.print("additional facilities (" + existing.getAdditionalFacilities() + "): ");
        String af = sc.nextLine().trim();
        if (af.isEmpty()) af = existing.getAdditionalFacilities();

        System.out.print("region (" + existing.getRegion() + "): ");
        String region = sc.nextLine().trim();
        if (region.isEmpty()) region = existing.getRegion();

        return new Property(existing.getAdsId(), name, year, rent, loc, type, rooms, parking, bath,
                size, furnished, fac, af, region);
    }
}
