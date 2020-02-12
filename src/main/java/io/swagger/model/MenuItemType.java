package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets MenuItemType
 */
public enum MenuItemType {

    CRUST_S("CRUST_S"),

    CRUST_M("CRUST_M"),

    CRUST_L("CRUST_L"),


    CHEESE("CHEESE"),

    SAUCE("SAUCE"),

    TOPPING("TOPPING");


    private String value;

    /**
     * String value of the object
     *
     * @param value
     * @return value
     */
    MenuItemType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static MenuItemType fromValue(String text) {
        for (MenuItemType b : MenuItemType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}

