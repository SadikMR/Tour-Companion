package com.example.tour;

public class SessionManager {
    private static SessionManager instance = new SessionManager();
    private String username;

    private SessionManager() {
        // Private constructor to prevent instantiation.
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public void setLoggedInUser(String username) {
        this.username = username;
    }

    public String getLoggedInUser() {
        return username;
    }

    public void clearSession() {
        username = null;
    }
}
