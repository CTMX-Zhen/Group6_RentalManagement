/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmanagementsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CSVLoader {

    private static final String FILE_PATH = "src/csv/mudah-apartment-kl-selangor.csv";

    public List<Rental> loadFromCSV() {
        List<Rental> rentals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1);
                if (values.length < 11) {
                    continue;
                }

                long id = Long.parseLong(values[0].trim());
                String propName = values[1].trim();
                String completionYear = values[2].trim();
                double monthlyRent = parseRent(values[3].trim());
                String location = values[4].trim();
                String propertyType = values[5].trim();
                String rooms = values[6].trim();
                String parking = values[7].trim();
                String bathroom = values[8].trim();
                String size = values[9].trim();
                String furnished = values[10].trim();

                rentals.add(new Rental(id, propName, completionYear, monthlyRent,
                        location, propertyType, rooms, parking, bathroom, size, furnished));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    private double parseRent(String rentStr) {
        rentStr = rentStr.replace("RM", "").replace("per month", "").replace(" ", "");
        try {
            return Double.parseDouble(rentStr);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
