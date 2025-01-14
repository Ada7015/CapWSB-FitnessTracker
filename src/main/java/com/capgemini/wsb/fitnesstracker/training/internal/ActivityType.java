package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing different types of physical activities.
 * Each activity is associated with a display name.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    /**
     * Constructs an {@code ActivityType} enum with the specified display name.
     *
     * @param displayName the display name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the activity type.
     *
     * @return the display name of the activity type
     */
    public String getDisplayName() {
        return displayName;
    }
}
