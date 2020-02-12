package io.swagger.api;

import io.swagger.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.Pizza;
import io.swagger.model.Store;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.PizzaRepository;
import io.swagger.repository.StoreRepository;
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

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-10T04:12:11.857Z")

@Controller
public class OrderApiController implements OrderApi {

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private StoreRepository storeRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteAllCustomerOrders() {
        String accept = request.getHeader("Accept");
        if (orderRepository.count() > 0) {
            orderRepository.deleteAll();
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value = "/order/{orderID}")
    public ResponseEntity<Void> deleteOrderById(@ApiParam(value = "orderID that is deleted", required = true) @PathVariable("orderID") String orderID) {
        String accept = request.getHeader("Accept");
        orderRepository.deleteById(orderID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Order>> getAllCustomersOrders() {
        List<Order> orderList = orderRepository.findAll();
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderByID(@ApiParam(value = "the order ID is used to get that specific order", required = true) @PathVariable("orderID") String orderID) {
        Order order = orderRepository.findById(orderID);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<Order> postCustomerOrder(@ApiParam(value = "Order objected that is posted", required = true) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        // Post by nature deletes all entities in database before adding the new value
        orderRepository.deleteAll();
        List<Pizza> pizzaList = body.getPizzas();
        Double totalPrice = 0.0;
        for (Pizza pizza : pizzaList) {
            totalPrice += pizza.getPizzaPrice();
        }
        body.setTotalPrice(totalPrice);
        // Then put the entity in the database
        orderRepository.save(body);
        return new ResponseEntity<Order>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<Order> putCustomerOrder(@ApiParam(value = "Order object that is updated", required = true) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        orderRepository.save(body);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{orderID}/pizza", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> getAllPizzasByOrderID(@ApiParam(value = "orderID to get all pizza list in that specific order", required = true) @PathVariable("orderID") String orderID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);
        List<Pizza> pizzas = order.getPizzas();
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderID}/pizza", method = RequestMethod.DELETE)
    public ResponseEntity<List<Pizza>> deleteAllPizzasByOrderID(@ApiParam(value = "orderID to delete all pizzas in that specific order", required = true) @PathVariable("orderID") String orderID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);
        List<Pizza> pizzas = order.getPizzas();
        //In our original method we forgot to delete the order from repository, therefore the order is never deleted and our implementation is not correct for the website
        orderRepository.deleteById(orderID);
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderID}/pizza/{pizzaID}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizzaByIDAndOrderID(@ApiParam(value = "orderID to get the pizza in that specific order", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "pizzaID in that specific order", required = true) @PathVariable("pizzaID") String pizzaID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);

        List<Pizza> pizzas = order.getPizzas();
        Pizza pizza = pizzaRepository.findById(pizzaID);
        if (pizzas.contains(pizzaID)) {
            return new ResponseEntity<>(pizza, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/order/{orderID}/pizza/{pizzaID}", method = RequestMethod.PUT)
    public ResponseEntity<Order> addPizzaByIDAndOrderID(@ApiParam(value = "orderID that the pizza is being added to", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "pizzaID that is being added to the order", required = true) @PathVariable("pizzaID") String pizzaID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);

        orderRepository.deleteById(order.getId());

        //Make menu with new item
        Pizza pizzaToAddToStore = pizzaRepository.findById(pizzaID);
        List<Pizza> pizzas = order.getPizzas();
        pizzas.add(pizzaToAddToStore);
        order.setPizzas(pizzas);
        //this line to set the total price of the order
        Double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.getPizzaPrice();
        }
        order.setTotalPrice(totalPrice);

        // Put Store object in Store Repo
        orderRepository.save(order);


        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{orderID}/pizza/{pizzaID}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePizzaByOrder(@ApiParam(value = "orderID is used to delete pizza", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "pizzaID that is being deleted from the order", required = true) @PathVariable("pizzaID") String pizzaID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);

        //Make menu with new item
        Pizza pizzaToDeleteFromStore = pizzaRepository.findById(pizzaID);
        List<Pizza> listOfPizzas = order.getPizzas();
        // delete this menu item from this Store's menu list
        listOfPizzas.remove(pizzaToDeleteFromStore);
        order.setPizzas(listOfPizzas);
        Double totalPrice = 0.0;
        for (Pizza pizza : listOfPizzas) {
            totalPrice += pizza.getPizzaPrice();
        }
        order.setTotalPrice(totalPrice);
        //In our original method we did not have this and forgot to add this line, therefore our website will not able able complete this implementation
        orderRepository.deleteById(orderID);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderID}/store/{storeID}", method = RequestMethod.POST)
    public ResponseEntity<Order> postStoreByIDAndOrderID(@ApiParam(value = "orderID that is created", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "storeID that is added to the order", required = true) @PathVariable("storeID") String storeID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);
        orderRepository.deleteById(orderID);
        order.setStoreID(storeID);
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{orderID}/store/{storeID}", method = RequestMethod.GET)
    public ResponseEntity<Store> getStoreByIDAndOrderID(@ApiParam(value = "get order ID", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "gett store ID ", required = true) @PathVariable("storeID") String storeID) {

        Store store = storeRepository.findById(storeID);

        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{orderID}/store/{storeID}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteStoreByIDAndOrderID(@ApiParam(value = "orderID that needs to be updated", required = true) @PathVariable("orderID") String orderID, @ApiParam(value = "storeID that is being deleted", required = true) @PathVariable("storeID") String storeID) {
        String accept = request.getHeader("Accept");
        Order order = orderRepository.findById(orderID);
        orderRepository.deleteById(orderID);

        order.setStoreID("");

        orderRepository.save(order);


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
