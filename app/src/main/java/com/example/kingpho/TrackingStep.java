package com.example.kingpho;

public class TrackingStep {

    private String description;
    private int icon;

    public TrackingStep(String description, int icon) {
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public int getIcon() {
        return icon;
    }
}
