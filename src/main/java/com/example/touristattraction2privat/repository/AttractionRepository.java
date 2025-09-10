package com.example.touristattraction2privat.repository;

import com.example.touristattraction2privat.model.Tag;
import com.example.touristattraction2privat.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class AttractionRepository {
    List<TouristAttraction> attractionList = getAllAttractions();

    public List<TouristAttraction> getAllAttractions() {
        File file = new File("src/main/resources/data/attractions.csv");
        List<TouristAttraction> attractionList = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            String txtLine = reader.nextLine();
            while (reader.hasNextLine()){

                txtLine = reader.nextLine();
                String[] txtLineSplit = txtLine.split(";");

                int idAsInt = Integer.parseInt(txtLineSplit[0]);


                String[] tagsSplit = txtLineSplit[3].split(",");
                ArrayList<Tag> tagArrayList = new ArrayList<>();
                for (String tag : tagsSplit) {
                    tagArrayList.add(Tag.valueOf(tag));
                }


                TouristAttraction touristAttraction = new TouristAttraction(idAsInt,txtLineSplit[1],
                        txtLineSplit[2],tagArrayList);

                attractionList.add(touristAttraction);

            } reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return attractionList;
    }

    public TouristAttraction getAttractionFromName(String name) {
        for (TouristAttraction attraction: attractionList){
            if (name.equalsIgnoreCase(attraction.getName())){
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction saveAttraction() {
        try {

        }catch ()

    }
}
