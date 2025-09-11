package com.example.touristattraction2privat.repository;

import com.example.touristattraction2privat.model.Tag;
import com.example.touristattraction2privat.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Repository
public class AttractionRepository {

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
        for (TouristAttraction attraction: getAllAttractions()){
            if (name.equalsIgnoreCase(attraction.getName())){
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction saveAttraction(TouristAttraction attraction) {
        try {
            File file = new File("src/main/resources/data/attractions.csv");
            FileWriter outFile = new FileWriter(file, true);

            outFile.write(
                    "\n" +attraction.getAttractionId() + ";" + attraction.getName() +
                            ";" + attraction.getDescription() + ";"

            );

            ArrayList<Tag> attractionTags = attraction.getTags();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < attractionTags.size(); i++) {
                builder.append(attractionTags.get(i));
                if (i < attractionTags.size() - 1) {
                    builder.append(",");
                }
            }
            outFile.write(builder.toString());
            outFile.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
        return attraction;
    }
}
