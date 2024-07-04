package com.example.kingpho;

public class TrackingStep {
    private String title;
    private String subtitle;
    private int iconResId;
    private String status;

    public TrackingStep(String title, String subtitle, int iconResId, String status) {
        this.title = title;
        this.subtitle = subtitle;
        this.iconResId = iconResId;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getStatus() {
        return status;
    }
}
