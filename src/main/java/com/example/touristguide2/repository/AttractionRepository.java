package com.example.touristguide2.repository;

import com.example.touristguide2.model.Tag;
import com.example.touristguide2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AttractionRepository {
    private List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("SMK", "Museum for Kunst", "København", List.of(Tag.KUNST, Tag.MUSEUM)),
            new TouristAttraction("Odense Zoo", "Europas bedste zoo", "Odense", List.of(Tag.BØRNEVENLIG)),
            new TouristAttraction("Dyrehaven", "Naturpark med skovområder", "Kongens Lyngby", List.of(Tag.NATUR, Tag.GRATIS)),
            new TouristAttraction("Tivoli", "Forlystelsespark midt i Københavns centrum", "København", List.of(Tag.BØRNEVENLIG))
    ));

    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    public TouristAttraction getAttractionFromName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equals(name)) {
                return attraction;
            }
        }
        return null;
    }

    public void saveAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public void deleteAttraction(String name) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equals(name)) {
                attractions.remove(i);
                return;
            }
        }
    }

    public void updateAttraction(TouristAttraction attraction) {
        for (int i = 0; i < attractions.size(); i++) {
            TouristAttraction existing = attractions.get(i);
            if (existing.getName().equalsIgnoreCase(attraction.getName())) {
                attractions.set(i, attraction);
                break;
            }
        }
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            if (!cities.contains(attraction.getCity())) {
                cities.add(attraction.getCity());
            }
        }
        return cities;
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        for (TouristAttraction attraction : attractions) {
            for (Tag tag : attraction.getTags()) {
                if (!tags.contains(tag)) {
                    tags.add(tag);
                }
            }
        }
        return tags;
    }
}