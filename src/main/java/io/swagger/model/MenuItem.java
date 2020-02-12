package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * MenuItem
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-10T22:26:37.675Z")
@Document(collection = "menuItem")
public class MenuItem {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("storeID")
    private String storeID = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("price")
    private Double price = null;

    @JsonProperty("calories")
    private Integer calories = null;

    @JsonProperty("isGlutenFree")
    private Boolean isGlutenFree = false;

    @JsonProperty("menuItemType")
    private Object menuItemType = null;


    /**
     * default constructor for menuItem
     */
    public MenuItem() {
    }

    /**
     * Constructor for MenuItem
     *
     * @param name
     * @param price
     * @param calories
     * @param isGlutenFree
     * @param menuItemType
     */
    @JsonCreator
    public MenuItem(@JsonProperty("name") String name, @JsonProperty("price") Double price, @JsonProperty("calories") Integer calories, @JsonProperty("isGlutenFree") Boolean isGlutenFree, @JsonProperty("menuItemType") Object menuItemType) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.isGlutenFree = isGlutenFree;
        this.menuItemType = menuItemType;
    }


    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public String getID() {
        return id;
    }

    /**
     * setId for menuItem
     *
     * @param id
     */

    public void setID(String id) {
        this.id = id;
    }

    /**
     * Get the storeId of the menuItem
     *
     * @param storeID
     * @return storeID
     */

    public MenuItem storeID(String storeID) {
        this.storeID = storeID;
        return this;
    }

    /**
     * Get storeID
     *
     * @return storeID
     **/
    @ApiModelProperty(value = "")
    public String getStoreID() {
        return storeID;
    }

    /**
     * Set store Id
     *
     * @param storeID
     */
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    /**
     * Name the menuItem
     *
     * @param name
     * @return menuItem
     */
    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getName() {
        return name;
    }


    /**
     * Set the name of the menuItem
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the price of the menuItem
     *
     * @param price
     * @return price
     */

    public MenuItem price(Double price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     *
     * @return price
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid
    public Double getPrice() {
        return price;
    }


    /**
     * Set price
     *
     * @return price
     **/
    public void setPrice() {
        if (menuItemType.equals(MenuItemType.TOPPING)) {
            this.price = new Double(2.00);
        } else if (menuItemType.equals(MenuItemType.CRUST_S)) {
            this.price = new Double(8.00);
        } else if (menuItemType.equals(MenuItemType.CRUST_M)) {
            this.price = new Double(10.00);
        } else if (menuItemType.equals(MenuItemType.CRUST_L)) {
            this.price = new Double(12.00);
        } else {
            this.price = new Double(0);
        }
    }

    /**
     * Set calories
     *
     * @param calories
     * @return calories
     **/
    public MenuItem calories(Integer calories) {
        this.calories = calories;
        return this;
    }

    /**
     * Get calories
     *
     * @return calories
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Integer getCalories() {
        return calories;
    }


    /**
     * Set calories
     *
     * @param calories
     * @return calories
     **/
    public void setCalories(Integer calories) {
        // This caloric count is for a small crust/pizzaSize
        if (menuItemType.equals(MenuItemType.CRUST_S)) {
            if (isGlutenFree) {
                this.calories = 130;
            } else {
                this.calories = 185;
            }

        } else if (menuItemType.equals(MenuItemType.CRUST_M)) {
            if (isGlutenFree) {
                this.calories = 200;
            } else {
                this.calories = 245;
            }
        } else if (menuItemType.equals(MenuItemType.CRUST_L)) {
            if (isGlutenFree) {
                this.calories = 275;
            } else {
                this.calories = 325;
            }
        } else if (menuItemType.equals(MenuItemType.CHEESE)) {
            this.calories = 80;
        } else if (menuItemType.equals(MenuItemType.SAUCE)) {
            this.calories = 75;
        } else if (menuItemType.equals(MenuItemType.TOPPING)) {
            this.calories = 100;
        } else {
            this.calories = 185;
        }
    }

    /**
     * Set the isGlutenFree
     *
     * @param isGlutenFree
     * @return boolean true, or false
     */
    public MenuItem isGlutenFree(Boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
        return this;
    }

    /**
     * Get isGlutenFree
     *
     * @return isGlutenFree
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Boolean isIsGlutenFree() {
        return isGlutenFree;
    }


    /**
     * Set isGlutenFree
     *
     * @return isGlutenFree
     **/
    public void setIsGlutenFree(Boolean isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }


    /**
     * Set the menuItemType
     *
     * @param menuItemType
     */
    public MenuItem menuItemType(Object menuItemType) {
        this.menuItemType = menuItemType;
        return this;
    }

    /**
     * Get menuItemType
     *
     * @return menuItemType
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull
    public Object getMenuItemType() {
        return menuItemType;
    }

    /**
     * Set the menuItemType
     *
     * @param menuItemType
     */
    public void setMenuItemType(Object menuItemType) {
        this.menuItemType = menuItemType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(this.id, menuItem.id) &&
                Objects.equals(this.name, menuItem.name) &&
                Objects.equals(this.price, menuItem.price) &&
                Objects.equals(this.calories, menuItem.calories) &&
                Objects.equals(this.isGlutenFree, menuItem.isGlutenFree) &&
                Objects.equals(this.menuItemType, menuItem.menuItemType);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, calories, isGlutenFree, menuItemType);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MenuItem {\n");


        sb.append("    menuItemID: ").append(toIndentedString(id)).append("\n");
        sb.append("    storeID: ").append(toIndentedString(storeID)).append("\n");

        sb.append("    menuItemID: ").append(toIndentedString(id)).append("\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    calories: ").append(toIndentedString(calories)).append("\n");
        sb.append("    isGlutenFree: ").append(toIndentedString(isGlutenFree)).append("\n");
        sb.append("    menuItemType: ").append(toIndentedString(menuItemType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

