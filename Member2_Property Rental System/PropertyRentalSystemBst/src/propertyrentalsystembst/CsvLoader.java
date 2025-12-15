/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

/**
 *
 * @author User
 */
public class CsvLoader {

    private final String path;

    public CsvLoader(String path) {
        this.path = path;
    }

    public List<Property> load() throws IOException {
        List<Property> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            String row;
            while ((row = br.readLine()) != null) {
                String[] a = splitCsv(row);
                if (a.length < 14) continue;

                long adsId = parseLong(a[0], -1L);
                if (adsId < 0) continue;

                String propName = trimOrEmpty(a[1]);
                String completionYear = trimOrEmpty(a[2]);
                double monthlyRent = parseMonthlyRent(a[3]); 
                String location = trimOrEmpty(a[4]);
                String propertyType = trimOrEmpty(a[5]);
                String rooms = trimOrEmpty(a[6]);
                String parking = trimOrEmpty(a[7]);
                String bathroom = trimOrEmpty(a[8]);
                String size = trimOrEmpty(a[9]);
                String furnished = trimOrEmpty(a[10]);
                String facilities = trimOrEmpty(a[11]);
                String additionalFacilities = trimOrEmpty(a[12]);
                String region = trimOrEmpty(a[13]);

                Property p = new Property(adsId, propName, completionYear, monthlyRent,
                        location, propertyType, rooms, parking, bathroom, size, furnished,
                        facilities, additionalFacilities, region);
                out.add(p);
            }
        }
        return out;
    }

    private static double parseMonthlyRent(String s) {
        if (s == null) return 0.0;
        String cleaned = s.replace("RM", "")
                          .replace("per month", "")
                          .replace(",", "")
                          .replaceAll("\\s+", "")
                          .trim();
        try { return Double.parseDouble(cleaned); } catch (Exception e) { return 0.0; }
    }

    private static long parseLong(String s, long def) {
        try { return Long.parseLong(s.trim()); } catch (Exception e) { return def; }
    }

    private static String trimOrEmpty(String s) {
        return s == null ? "" : s.trim();
    }

    private static String[] splitCsv(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQ = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (inQ) {
                if (c == '"') {
                    if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                        sb.append('"'); i++; 
                    } else {
                        inQ = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if (c == ',') {
                    out.add(sb.toString()); sb.setLength(0);
                } else if (c == '"') {
                    inQ = true;
                } else {
                    sb.append(c);
                }
            }
        }
        out.add(sb.toString());
        return out.toArray(new String[0]);
    }
}
