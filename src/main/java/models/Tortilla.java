package models;

public enum Tortilla {
    FLOUR("Flour"),
    CORN("Corn"),
    HARD_SHELL("Hard Shell"),
    BOWL("Bowl");

    private final String displayName;

    Tortilla(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
