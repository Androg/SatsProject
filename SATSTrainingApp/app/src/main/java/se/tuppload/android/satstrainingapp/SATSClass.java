package se.tuppload.android.satstrainingapp;


public class SATSClass {
    public final String name;
    public final String description;

    public SATSClass(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
