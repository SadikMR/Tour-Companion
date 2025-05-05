package com.example.tour;

import javafx.scene.control.Button;

public class Guide {
    private String name;
    private int successfulService;
    private float ratings;
    private Button button;
    private boolean booked;

    public Guide(String name, int successfulService, float ratings) {
        this.name = name;
        this.successfulService = successfulService;
        this.ratings = ratings;
        this.button = new Button("Book");
        this.button.setOnAction(event -> bookGuide());
    }
    public void bookGuide() {
        this.booked = true;
        this.button.setText("Booked");
        this.button.setDisable(true);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public int getSuccessfulService() {
        return successfulService;
    }

    public float getRatings() {
        return ratings;
    }
}
