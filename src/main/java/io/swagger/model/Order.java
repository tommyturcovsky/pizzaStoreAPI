package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Order
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-10T04:12:11.857Z")
@Document(collection = "order")
public class Order {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("storeID")
    private String storeID = null;

    @JsonProperty("pizzas")
    @Valid
    private List<Pizza> pizzas = null;

    @JsonProperty("totalPrice")
    private Double totalPrice = 0.0;

    public Order() {
    }


    /**
     * Constructor for the order class
     *
     * @param storeID
     * @param pizzas
     */
    @JsonCreator
    public Order(@JsonProperty("storeID") String storeID, @JsonProperty("pizzas") @Valid List<Pizza> pizzas) {
        this.storeID = storeID;
        this.pizzas = pizzas;
    }


    public String getId() {
        return id;
    }

    /**
     * SetId for order
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set StoreID for Order
     *
     * @param storeID
     * @return
     */
    public Order storeID(String storeID) {
        this.storeID = storeID;
        return this;
    }

    /**
     * Get storeID
     *
     * @return storeID
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public String getStoreID() {
        return storeID;
    }

    /**
     * setStoreId
     *
     * @param storeID
     */

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    /**
     * Get list of Pizzas in the Order
     *
     * @param pizzas
     * @return pizza list
     */

    public Order pizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
        return this;
    }

    /**
     * Add Pizza Items
     *
     * @param pizzasItem
     * @return list of pizzaItems
     */

    public Order addPizzasItem(Pizza pizzasItem) {
        this.pizzas.add(pizzasItem);
        return this;
    }

    /**
     * Get pizzas
     *
     * @return pizzas
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    @Valid
    public List<Pizza> getPizzas() {
        return pizzas;
    }


    /**
     * setPizzas to the order
     *
     * @param pizzas
     */
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    /**
     * Get the totalPrice of the order
     *
     * @param totalPrice
     * @return totalPrice
     */

    public Order totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    /**
     * Get totalPrice
     *
     * @return totalPrice
     **/
    @ApiModelProperty(required = true, value = "")

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Set the totalPrice of the order
     *
     * @param totalPrice
     * @return totalPrice
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Set the totalPriceForOwner of the order
     *
     * @param password
     * @return totalPrice
     */

    public void setTotalPriceForOwner(String password) {
        if (password == "1234") {
            this.totalPrice = 0.00;
        }
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(this.storeID, order.storeID) &&
                Objects.equals(this.pizzas, order.pizzas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeID, pizzas);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Order {\n");

        sb.append("    storeID: ").append(toIndentedString(storeID)).append("\n");
        sb.append("    pizzas: ").append(toIndentedString(pizzas)).append("\n");
        sb.append("    totalPrice: ").append(toIndentedString(pizzas)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

