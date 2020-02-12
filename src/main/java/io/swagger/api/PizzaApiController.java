package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.MenuItem;
import io.swagger.model.Pizza;

import io.swagger.repository.MenuItemRepository;
import io.swagger.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T01:01:57.885Z")


@Controller
public class PizzaApiController implements PizzaApi {

    private static final Logger log = LoggerFactory.getLogger(PizzaApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;


    @org.springframework.beans.factory.annotation.Autowired
    public PizzaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Pizza> createPizza(@ApiParam(value = "add pizza object", required = true) @Valid @RequestBody Pizza pizza) {
        String accept = request.getHeader("Accept");
        pizza.setPizzaCalories();
        pizza.setPizzaPrice();
        pizzaRepository.save(pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/pizza/{pizzaID}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePizzaByID(@ApiParam(value = "ID of the pizza", required = true) @PathVariable("pizzaID") String pizzaID) {
        String accept = request.getHeader("Accept");
        pizzaRepository.deleteById(pizzaID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        List<Pizza> pizza = pizzaRepository.findAll();
        return new ResponseEntity<List<Pizza>>(pizza, HttpStatus.OK);
    }


    @RequestMapping(value = "/pizza/{pizzaID}/menuItem", method = RequestMethod.GET)
    public ResponseEntity<List<MenuItem>> getMenuItemsByPizzaID(@ApiParam(value = "the pizza ID", required = true) @PathVariable("pizzaID") String pizzaID) {
        Pizza pizza = pizzaRepository.findById(pizzaID);
        List<MenuItem> menuItemsInPizza = pizza.getMenuItems();
        return new ResponseEntity<List<MenuItem>>(menuItemsInPizza, HttpStatus.OK);
    }


    @RequestMapping(value = "/pizza/{pizzaID}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizzaByID(@ApiParam(value = "the pizza ID", required = true) @PathVariable("pizzaID") String pizzaID) {
        Pizza pizza = pizzaRepository.findById(pizzaID);

        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }


    @RequestMapping(value = "/pizza/{pizzaID}/menuItem/{menuItemID}", method = RequestMethod.PUT)
    public ResponseEntity<Pizza> updateMenuItemByIDAndPizzaID(@ApiParam(value = "pizzaID to be updated", required = true) @PathVariable("pizzaID") String pizzaID, @ApiParam(value = "menuItem to be updated", required = true) @PathVariable("menuItemID") String menuItemID) {
        String accept = request.getHeader("Accept");
        Pizza pizza = pizzaRepository.findById(pizzaID);
        pizzaRepository.deleteById(pizzaID);
        MenuItem menu = menuItemRepository.findById(menuItemID);
        pizza.addMenuItemItem(menu);
        pizza.setPizzaCalories();
        pizza.setPizzaPrice();
        pizzaRepository.save(pizza);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }
}


