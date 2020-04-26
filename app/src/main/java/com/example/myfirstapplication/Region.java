package com.example.myfirstapplication;

public class Region {

    private String name;
    private String description;
    private String story;
    private String image;

    public Region(String name, String description, String story, String image) {
        this.name = name;
        this.description = description;
        this.story = story;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStory() {
        return story;
    }

    public String getImage() {
        return image;
    }

}
