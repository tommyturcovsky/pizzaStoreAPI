package io.swagger.model;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets PizzaSize
 */
public enum PizzaSize {

    CRUST_SMALL("CRUST_SMALL"),

    CRUST_MEDIUM("CRUST_MEDIUM"),

    CRUST_LARGE("CRUST_LARGE");

    private String value;


    /**
     * String value of the object
     *
     * @param value
     * @return value
     */
    PizzaSize(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static PizzaSize fromValue(String text) {
        for (PizzaSize b : PizzaSize.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}

