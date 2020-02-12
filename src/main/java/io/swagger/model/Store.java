package io.swagger.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * This represents the Store class that holds the store ID, name, address, glutenFree status, and the store menu.
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-29T21:29:36.730Z")
@Document(collection = "store")
public class Store {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("storeName")
    private String storeName = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("glutenFree")
    private Boolean glutenFree = false;

    @JsonProperty("storeMenu")
    @Valid
    private List<MenuItem> storeMenu = null;

    /**
     * Default Constructor
     */
    public Store() {
    }

    /**
     * Constructor to pass json objects through to be deserialized
     *
     * @param storeName
     * @param address
     * @param glutenFree
     * @param storeMenu
     */
    @JsonCreator
    public Store(@JsonProperty("storeName") String storeName, @JsonProperty("address") String address, @JsonProperty("glutenFree") Boolean glutenFree, @JsonProperty("storeMenu") @Valid List<MenuItem> storeMenu) {
        this.storeName = storeName;
        this.address = address;
        this.glutenFree = glutenFree;
        this.storeMenu = storeMenu;
    }

    public Store storeID(String storeID) {
        this.id = storeID;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(example = "string", value = "")

    public String getId() {
        return id;
    }

    /**
     * SetId for store
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set the storeName
     *
     * @param storeName
     * @return storeName
     */
    public Store storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    /**
     * Get storeName
     *
     * @return storeName
     **/
    @ApiModelProperty(value = "")
    public String getStoreName() {
        return storeName;
    }

    /**
     * Set the storeName
     *
     * @param storeName
     * @return storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * Set address
     *
     * @param address
     * @return address
     **/

    public Store address(String address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     *
     * @return address
     **/
    @ApiModelProperty(value = "")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Store glutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
        return this;
    }

    /**
     * Get glutenFree
     *
     * @return glutenFree
     **/
    @ApiModelProperty(value = "")

    public Boolean isGlutenFree() {
        return glutenFree;
    }

    /**
     * Set glutenFree
     *
     * @return glutenFree
     **/
    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    /**
     * Get storeMenu
     *
     * @param storeMenu
     * @return storeMenu
     **/
    public Store storeMenu(List<MenuItem> storeMenu) {
        this.storeMenu = storeMenu;
        return this;
    }


    /**
     * Add the storeMenuItem, adding a menuItem to the a particular store
     *
     * @param storeMenuItem
     * @return menuItem
     */
    public Store addStoreMenuItem(MenuItem storeMenuItem) {
        if (this.storeMenu == null) {
            this.storeMenu = new ArrayList<MenuItem>();
        }
        this.storeMenu.add(storeMenuItem);
        return this;
    }

    /**
     * Get storeMenu
     *
     * @return storeMenu
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<MenuItem> getStoreMenu() {
        return storeMenu;
    }


    /**
     * SetStoreMenu
     *
     * @param storeMenu
     */
    public void setStoreMenu(List<MenuItem> storeMenu) {
        this.storeMenu = storeMenu;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Store store = (Store) o;
        return Objects.equals(this.id, store.id) &&
                Objects.equals(this.storeName, store.storeName) &&
                Objects.equals(this.address, store.address) &&
                Objects.equals(this.glutenFree, store.glutenFree) &&
                Objects.equals(this.storeMenu, store.storeMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, storeName, address, glutenFree, storeMenu);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Store {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    storeName: ").append(toIndentedString(storeName)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    glutenFree: ").append(toIndentedString(glutenFree)).append("\n");
        sb.append("    storeMenu: ").append(toIndentedString(storeMenu)).append("\n");
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

