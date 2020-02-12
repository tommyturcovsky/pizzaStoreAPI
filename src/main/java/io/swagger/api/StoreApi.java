package io.swagger.api;

import io.swagger.annotations.*;

import io.swagger.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.model.Store;

import javax.validation.Valid;
import java.util.List;

@Api(value = "store", description = "the store api")
public interface StoreApi {

    @ApiOperation(value = "list ALL store locations", nickname = "getAllLocations", notes = "This lists all of the pizza store locations", response = Store.class, responseContainer = "List", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returned all the store locations", response = Store.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Store>> getAllLocations();

    @ApiOperation(value = "adds a new store as a POST method", nickname = "addNewStorePost", notes = "", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully posted to database!"),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Store> addNewStorePost(@ApiParam(value = "Message object to be added to the chat room", required = true) @Valid @RequestBody Store body);

    @ApiOperation(value = "adds a new store as a PUT method", nickname = "addNewStore", notes = "", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully posted to database!"),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store",
            produces = {"application/json", "application/xml"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Store> addNewStorePut(@ApiParam(value = "Message object to be added to the chat room", required = true) @Valid @RequestBody Store body);

    @ApiOperation(value = "delete all Store objects in database", nickname = "deleteAllStores", notes = "Deletes all Stores in database", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returned all the menu items at a given store", response = Store.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity deleteAllStores();

    @ApiOperation(value = "get a store by id", nickname = "getStoreById", notes = "This gets a pizza store instance by its ID", response = Object.class, tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully a store instance", response = Object.class),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store/{storeID}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Store> getStoreById(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeID") String storeID);

    @ApiOperation(value = "delete Store object by it's String id", nickname = "deleteStoreById", notes = "", response = Store.class, responseContainer = "List", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returned all the menu items at a given store", response = Store.class),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store/{storeId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity deleteStoreById(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeId") String id);

    @ApiOperation(value = "returns a menuItem instance being offered by the given storeID", nickname = "getMenuItemByIDAndStoreID", notes = "Returns the menu item being offered by the given store", response = Object.class, tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successfully returned all the menu items at a given store", response = Object.class),
            @ApiResponse(code = 400, message = "bad input parameter, object invalid")})
    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<MenuItem> getMenuItemByIDAndStoreID(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the menu item", required = true) @PathVariable("menuItemID") String menuItemID);

    @ApiOperation(value = "creates item in the menu for a specific store", nickname = "addMenuItemByIDAndStoreID", notes = "creates a menu item at a given pizza store", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid input")})
    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Store> addMenuItemByIDAndStoreID(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the menu item", required = true) @PathVariable("menuItemID") String menuItemID);

    @ApiOperation(value = "deletes a menu item", nickname = "deleteMenuItemByStore", notes = "Deletes a menu item", tags = {"Store",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successfully deleted order"),
            @ApiResponse(code = 400, message = "invalid input, object invalid")})
    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteMenuItemByStore(@ApiParam(value = "ID of the order", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the order", required = true) @PathVariable("menuItemID") String menuItemID);
}
