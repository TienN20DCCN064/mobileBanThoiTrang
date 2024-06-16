package com.example.quanlysach.utils;

public class MenuItem {
    private int buttonId;
    private String buttonText;

    public MenuItem(int buttonId, String buttonText) {
        this.buttonId = buttonId;
        this.buttonText = buttonText;
    }

    public int getButtonId() {
        return buttonId;
    }

    public String getButtonText() {
        return buttonText;
    }
}
