package com.example.touristattraction2privat.service;

import com.example.touristattraction2privat.model.TouristAttraction;
import com.example.touristattraction2privat.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {
    private AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository){
        this.attractionRepository = attractionRepository;
    }

    public List<TouristAttraction> getAllAttractions(){
        return attractionRepository.getAllAttractions();
    }

    public TouristAttraction getAttractionFromName(String name) {
        return attractionRepository.getAttractionFromName(name);
    }

    public TouristAttraction saveAttraction(TouristAttraction touristAttraction) {
        return attractionRepository.saveAttraction(touristAttraction);
    }
}
