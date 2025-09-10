package com.example.touristattraction2privat.model;

import java.util.ArrayList;

public class TouristAttraction {
    private final int attractionId;
    private String name;
    private String description;
    private ArrayList<Tag> tags;

    public TouristAttraction(int attractionId, String name, String description, ArrayList<Tag> tags) {
        this.attractionId = attractionId;
        this.name = name;
        this.description = description;
        this.tags = tags;

    }

    public TouristAttraction(){
    }

    public int getAttractionId(){
        return attractionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
