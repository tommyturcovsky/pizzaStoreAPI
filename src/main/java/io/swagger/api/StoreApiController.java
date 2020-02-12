package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.repository.MenuItemRepository;
import io.swagger.repository.StoreRepository;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Store;


@Controller
public class StoreApiController implements StoreApi {

    private static final Logger log = LoggerFactory.getLogger(Store.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    StoreRepository repository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public StoreApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Store>> getAllLocations() {
        List<Store> storeList = repository.findAll();
        return new ResponseEntity<List<Store>>(storeList, HttpStatus.OK);
    }


    public ResponseEntity<Store> addNewStorePost(@ApiParam(value = "Clears database and adds store Object", required = true) @Valid @RequestBody Store body) {
        String accept = request.getHeader("Accept");
        // Post by nature deletes all entities in database before adding the new value
        repository.deleteAll();
        // Then put the entity in the database
        repository.save(body);
        return new ResponseEntity<Store>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<Store> addNewStorePut(@ApiParam(value = "Adds store object to stores database", required = true) @Valid @RequestBody Store body) {
        String accept = request.getHeader("Accept");
        repository.save(body);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity deleteAllStores() {
        String accept = request.getHeader("Accept");
        if (repository.count() > 0) {
            repository.deleteAll();
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/store/{storeId}", method = RequestMethod.GET)
    public ResponseEntity<Store> getStoreById(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeId") String storeId) {
        Store store = repository.findById(storeId);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }


    @RequestMapping(value = "/store/{storeId}")
    public ResponseEntity deleteStoreById(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeId") String id) {
        String accept = request.getHeader("Accept");
        repository.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}", method = RequestMethod.GET)
    public ResponseEntity<MenuItem> getMenuItemByIDAndStoreID(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the menu item", required = true) @PathVariable("menuItemID") String menuItemID) {
        String accept = request.getHeader("Accept");
        Store store = repository.findById(storeID);

        //Make menu with new item
        List<MenuItem> storeMenu = store.getStoreMenu();
        MenuItem menuItem = menuItemRepository.findById(menuItemID);
        if (storeMenu.contains(menuItem)) {
            return new ResponseEntity<>(menuItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}", method = RequestMethod.PUT)
    public ResponseEntity<Store> addMenuItemByIDAndStoreID(@ApiParam(value = "ID of the given store", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the menu item", required = true) @PathVariable("menuItemID") String menuItemID) {
        //TODO: validation that the id's passed in are in repo
        String accept = request.getHeader("Accept");
        Store store = repository.findById(storeID);
        repository.deleteById(store.getId());

        //Make menu with new item
        MenuItem menuItemToAddToStore = menuItemRepository.findById(menuItemID);
        List<MenuItem> storeMenu = store.getStoreMenu();
        storeMenu.add(menuItemToAddToStore);
        store.setStoreMenu(storeMenu);

        // Put Store object in Store Repo
        repository.save(store);

        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/store/{storeID}/menuItem/{menuItemID}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMenuItemByStore(@ApiParam(value = "ID of the order", required = true) @PathVariable("storeID") String storeID, @ApiParam(value = "ID of the order", required = true) @PathVariable("menuItemID") String menuItemID) {
        String accept = request.getHeader("Accept");
        Store store = repository.findById(storeID);

        //Make menu with new item
        MenuItem menuItemToDeleteFromStore = menuItemRepository.findById(menuItemID);
        List<MenuItem> storeMenu = store.getStoreMenu();
        // delete this menu item from this Store's menu list
        storeMenu.remove(menuItemToDeleteFromStore);
        store.setStoreMenu(storeMenu);


        return new ResponseEntity<>(HttpStatus.OK);
    }


}
