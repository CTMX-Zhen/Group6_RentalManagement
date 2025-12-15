/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertyrentalsystembst;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class RentalService {

    private final Bst<Long, Property> byId = new Bst<Long, Property>();

    public void addAll(List<Property> props) {
        if (props == null) return;
        for (int i = 0; i < props.size(); i++) {
            Property p = props.get(i);
            if (p == null) continue;
            byId.insert(Long.valueOf(p.getAdsId()), p);
        }
    }

    public boolean add(Property p) {
        if (p == null) return false;
        Long key = Long.valueOf(p.getAdsId());
        // prevent duplicate ads_id
        if (byId.search(key) != null) return false;
        byId.insert(key, p);
        return true;
    }

    public Property get(long adsId) {
        return byId.search(Long.valueOf(adsId));
    }

    public boolean update(long adsId, Property updated) {
        if (updated == null) return false;
        Long key = Long.valueOf(adsId);
        Property existing = byId.search(key);
        if (existing == null) return false;

        existing.setPropName(updated.getPropName());
        existing.setCompletionYear(updated.getCompletionYear());
        existing.setMonthlyRent(updated.getMonthlyRent());
        existing.setLocation(updated.getLocation());
        existing.setPropertyType(updated.getPropertyType());
        existing.setRooms(updated.getRooms());
        existing.setParking(updated.getParking());
        existing.setBathroom(updated.getBathroom());
        existing.setSize(updated.getSize());
        existing.setFurnished(updated.getFurnished());
        existing.setFacilities(updated.getFacilities());
        existing.setAdditionalFacilities(updated.getAdditionalFacilities());
        existing.setRegion(updated.getRegion());
        return true;
    }

    public boolean delete(long adsId) {
        return byId.delete(Long.valueOf(adsId));
    }

    public List<Property> listAll() {
        return byId.inorderValues();  // Java-7 version from your Bst
    }

    public List<Property> filterByPriceRange(double min, double max, boolean onlyAvailable) {
        List<Property> src = byId.inorderValues();
        List<Property> out = new ArrayList<Property>();
        for (int i = 0; i < src.size(); i++) {
            Property p = src.get(i);
            if (p == null) continue;
            double rent = p.getMonthlyRent();
            if (rent >= min && rent <= max) {
                if (!onlyAvailable || p.isAvailable()) {
                    out.add(p);
                }
            }
        }
        return out;
    }

    public int size() {
        return byId.size();
    }
}
