package io.swagger.api;

import io.swagger.model.MenuItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-10T01:22:58.196Z")

@Controller
public class MenuItemApiController implements MenuItemApi {

    private static final Logger log = LoggerFactory.getLogger(MenuItemApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public MenuItemApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    public ResponseEntity<MenuItem> createMenuItem(@ApiParam(value = "add menuItem object", required = true) @Valid @RequestBody MenuItem menuItem) {
        String accept = request.getHeader("Accept");
        menuItemRepository.save(menuItem);
        return new ResponseEntity<MenuItem>(menuItem, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/menuItem/{menuItemID}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMenuItemById(@ApiParam(value = "ID of the menuItem", required = true) @PathVariable("menuItemID") String menuItemID) {
        String accept = request.getHeader("Accept");
        menuItemRepository.delete(menuItemID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<MenuItem>> getMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return new ResponseEntity<List<MenuItem>>(menuItems, HttpStatus.OK);
    }

    @RequestMapping(value = "/menuItem/{menuItemID}", method = RequestMethod.GET)
    public ResponseEntity<MenuItem> getMenuItemById(@ApiParam(value = "ID of the menuItem", required = true) @PathVariable("menuItemID") String menuItemID) {
        MenuItem menuItem = menuItemRepository.findById(menuItemID);
        return new ResponseEntity<MenuItem>(menuItem, HttpStatus.OK);
    }

}
