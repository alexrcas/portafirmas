package net.avantic.fmw;

public class Validation {

    private final String propertyName;
    private final String message;

    public Validation(String propertyName, String message) {
        this.propertyName = propertyName;
        this.message = message;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getMessage() {
        return message;
    }
}
