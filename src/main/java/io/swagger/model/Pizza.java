package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Pizza
 */
@Validated

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T01:01:57.885Z")
@Document(collection = "pizza")
public class Pizza {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("menuItems")
    @Valid
    private List<MenuItem> menuItems = null;

    @JsonProperty("pizzaSize")
    private Object pizzaSize;


    @JsonProperty("pizzaCalories")
    private Integer pizzaCalories = 0;

    @JsonProperty("pizzaPrice")
    private Double pizzaPrice = 0.0;

    @JsonProperty("isPizza")
    private Boolean isPizza = true;


    public Pizza pizzaId(String pizzaId) {
        this.id = pizzaId;
        return this;
    }

    /**
     * Default Constructor
     */
    public Pizza() {
    }

    /**
     * Constructor to pass json objects through to be deserialized
     *
     * @param menuItems
     * @param size
     * @param isPizza
     */
    @JsonCreator
    public Pizza(@JsonProperty("menuItems") @Valid List<MenuItem> menuItems, @JsonProperty("pizzaSize") PizzaSize size, @JsonProperty("isPizza") Boolean isPizza) {
        this.menuItems = menuItems;
        this.pizzaSize = size;
        this.isPizza = isPizza;
    }


    /**
     * Get pizzaId
     *
     * @return pizzaId
     **/
    @ApiModelProperty(value = "")
    public String getPizzaId() {
        return id;
    }

    /**
     * Set the pizzaId
     *
     * @param pizzaId
     */
    public void setPizzaId(String pizzaId) {
        this.id = pizzaId;
    }

    /**
     * Set the list of the menuItems
     *
     * @param menuItem
     * @return list of menuItems
     */
    public Pizza menuItem(List<MenuItem> menuItem) {
        this.menuItems = menuItem;
        return this;
    }

    /**
     * Add a menuItem to the pizza
     *
     * @param menuItemItem
     * @return menuItemItem
     */

    public Pizza addMenuItemItem(MenuItem menuItemItem) {
        if (this.menuItems == null) {
            this.menuItems = new ArrayList<MenuItem>();
        }
        this.menuItems.add(menuItemItem);

        return this;
    }

    /**
     * menu Item for this current pizza object
     *
     * @return menuItem
     **/


    @ApiModelProperty(required = true, value = "")
    @NotNull
    @Valid

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * setMenuItems to the pizza object
     *
     * @param menuItems
     * @return menuItems
     */
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    /**
     * Set the pizzaSize
     *
     * @param pizzaSize
     * @return pizzaSize
     */

    public Pizza pizzaSize(Object pizzaSize) {
        this.pizzaSize = pizzaSize;
        return this;
    }

    /**
     * Get pizzaSize
     *
     * @return pizzaSize
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public Object getPizzaSize() {
        return pizzaSize;
    }

    /**
     * Set the pizzaSize
     *
     * @param pizzaSize
     * @return pizzaSize
     */
    public void setPizzaSize(Object pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    /**
     * Set the pizzaCalories
     *
     * @param pizzaCalories
     * @return pizzaCalories
     */
    public Pizza pizzaCalories(Integer pizzaCalories) {
        this.pizzaCalories = pizzaCalories;
        return this;
    }

    /**
     * total calories for current pizza object
     *
     * @return pizzaCalories
     **/
    @ApiModelProperty(value = "total calories for current pizza object")

    /**
     * Get the pizzaCalories
     * @return pizzaCalories
     */

    public Integer getPizzaCalories() {
        return pizzaCalories;
    }


    /**
     * Set the pizzaCalories
     *
     * @return pizzaCalories
     */
    public void setPizzaCalories() {
        pizzaCalories = 0;
        for (MenuItem menuItem : this.menuItems) {
            System.out.println("menu item: " + menuItem);
            pizzaCalories += menuItem.getCalories();
        }
    }


    /**
     * Set the pizzaPrice
     *
     * @param pizzaPrice
     * @return pizzaPrice
     */
    public Pizza pizzaPrice(Double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;

        return this;
    }

    /**
     * menu Item for this pizza
     *
     * @return menuItem
     **/

    @ApiModelProperty(value = "total price for current pizza object")


    public Double getPizzaPrice() {
        return pizzaPrice;
    }


    /**
     * Set the pizzaPrice
     * calculates the cost of the pizza based on the menuItems
     *
     * @return pizzaPrice
     */

    public Double setPizzaPrice() {
        pizzaPrice = 0.0;
        for (MenuItem menuItem : this.menuItems) {
            pizzaPrice += menuItem.getPrice();
        }
        return pizzaPrice;
    }

    /**
     * Set the pizzaPrice
     *
     * @param pizzaPrice
     * @return pizzaPrice
     */

    public Pizza isPizza(Boolean isPizza) {
        this.isPizza = isPizza;
        return this;
    }

    /**
     * is the current pizza a complete pizza(including at least crust, cheese, sauce. May have 0 or more toppings)
     *
     * @return isPizza
     **/

    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Boolean isIsPizza() {
        return isPizza;
    }


    /**
     * Set isPizza
     *
     * @return isPizza
     **/
    public Boolean setIsPizza() {
        return isPizza;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pizza pizza = (Pizza) o;

        return Objects.equals(this.id, pizza.id) &&
                Objects.equals(this.menuItems, pizza.menuItems) &&
                Objects.equals(this.pizzaSize, pizza.pizzaSize) &&
                Objects.equals(this.pizzaCalories, pizza.pizzaCalories) &&
                Objects.equals(this.pizzaPrice, pizza.pizzaPrice) &&
                Objects.equals(this.isPizza, pizza.isPizza);

    }

    @Override
    public int hashCode() {

        return Objects.hash(id, menuItems, pizzaSize, pizzaCalories, pizzaPrice, isPizza);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pizza {\n");
        sb.append("    pizzaId: ").append(toIndentedString(id)).append("\n");
        sb.append("    menuItems: ").append(toIndentedString(menuItems)).append("\n");
        sb.append("    pizzaSize: ").append(toIndentedString(pizzaSize)).append("\n");
        sb.append("    pizzaCalories: ").append(toIndentedString(pizzaCalories)).append("\n");
        sb.append("    pizzaPrice: ").append(toIndentedString(pizzaPrice)).append("\n");
        sb.append("    isPizza: ").append(toIndentedString(isPizza)).append("\n");

        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

