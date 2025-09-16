package com.example.touristguide2.service;

import com.example.touristguide2.model.Tag;
import com.example.touristguide2.model.TouristAttraction;
import com.example.touristguide2.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  AttractionService {
    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository){
        this.attractionRepository = attractionRepository;
    }

    public List<TouristAttraction> getAttractions(){
        return attractionRepository.getAttractions();
    }

    public TouristAttraction getAttractionFromName(String name) {
        return attractionRepository.getAttractionFromName(name);
    }

    public void saveAttraction(TouristAttraction attraction) {
        attractionRepository.saveAttraction(attraction);
    }

    public void updateAttraction(TouristAttraction attraction) {
        attractionRepository.updateAttraction(attraction);
    }

    public void deleteAttraction(String name) {
        attractionRepository.deleteAttraction(name);
    }

    public List<String> getCities() {
        return attractionRepository.getCities();
    }

    public List<Tag> getTags() {
        return attractionRepository.getTags();
    }
}