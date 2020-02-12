package io.swagger.repository;

import io.swagger.api.OrderApiController;
import io.swagger.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.Swagger2SpringBoot;


import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest(classes = {Swagger2SpringBoot.class})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderApiController orderApiController;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Before
    public void setUp() {
        orderRepository.deleteAll();
    }

    @After
    public void tearDown() {
        orderRepository.deleteAll();
    }


    /**
     * Testing the create method in the order repository
     * Making sure the order is (created) saved in the order repository
     */

    @Test
    public void createOrderTest() {
        Order order = new Order();
        orderRepository.save(order);
        long pizzaCount = orderRepository.count();
        assertEquals(1, pizzaCount);
    }

    /**
     * Testing the delete method in the order repository
     */

    @Test
    public void deleteOrderTest() {

        Order order = new Order();
        orderRepository.save(order);
        orderRepository.deleteAll();
        long pizzaCount = orderRepository.count();
        assertEquals(0, pizzaCount);
    }


    /**
     * Testing the getPizzaByID method in the order repository
     */
    @Test
    public void getPizzaByIdTest() {
        Order order = new Order();
        Order saveOrderInDtbase = orderRepository.save(order);
        String id = order.getId();
        Order findOrder = orderRepository.findById(saveOrderInDtbase.getId());

        assertEquals(id, findOrder.getId());
    }

    /**
     * Testing the deleteOrderById method in the order repository
     */

    @Test
    public void deleteOrderByIdTest() {
        Order order = new Order();
        Order saveOrder = orderRepository.save(order);
        order.getId();
        orderRepository.delete(saveOrder.getId());

        assertEquals(0, orderRepository.count());
    }


    /**
     * Testing the deleteAll method in the order repository
     */
    @Test
    public void deleteAllOrders() {
        Order order1 = new Order();
        orderRepository.save(order1);
        Order order2 = new Order();
        orderRepository.save(order2);
        orderRepository.deleteAll();
        long count = orderRepository.count();
        assertEquals(0, count);
    }

    /**
     * Testing the the save method in the order repository
     */
    @Test
    public void getListOfOrderIdTest() {
        Order order = new Order();
        orderRepository.save(order);
        long count = orderRepository.count();
        assertEquals(1, count);
    }

    /**
     * Testing the post method in the order API Controller
     */


    @Test
    public void orderControllerPostMethod() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_L", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_sausage", 2.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);


        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        Assert.assertEquals(1, orderRepository.count());

    }

    /**
     * Testing the delete method in the order API Controller
     */
    @Test
    public void orderControllerDeleteMethod() {

        Order order = new Order();
        orderRepository.save(order);
        String id = order.getId();
        orderApiController.deleteOrderById(id);
        Assert.assertEquals(0, orderRepository.count());

    }

    /**
     * Testing the getOrderById method in the order API Controller
     */
    @Test
    public void orderControllerGetOrderByID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_M", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);


        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        String id = order.getId();


        Assert.assertEquals(id, order.getId());

    }

    /**
     * Testing the getCustomerOrder method in the order API Controller
     */
    @Test
    public void getListofPizzasInOrder() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_S", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);

        MenuItem itemList1 = new MenuItem("Crust_L", 15.0, 250, false, "CRUST");
        MenuItem itemList2 = new MenuItem("topping_onions", 15.0, 250, false, "TOPPING");
        MenuItem itemList3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem itemList4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItems2InPizza = new ArrayList<>();
        menuItems2InPizza.add(itemList1);
        menuItems2InPizza.add(itemList2);
        menuItems2InPizza.add(itemList3);
        menuItems2InPizza.add(itemList4);


        Pizza pizza2 = new Pizza(menuItems2InPizza, PizzaSize.CRUST_LARGE, true);
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizzaList.add(pizza2);


        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        order.getId();


        Assert.assertEquals(pizzaList, order.getPizzas());
    }

    /**
     * Testing the putCustomerOrder method in the order API Controller
     */

    @Test
    public void orderControllerPutMethod() {
        Order order = new Order();
        orderApiController.putCustomerOrder(order);
        Assert.assertEquals(1, orderRepository.count());
    }

    /**
     * Testing the deleteAllPizzasByOrderId method in the order API Controller
     */

    @Test
    public void deleteAllPizzas() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_S", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);


        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);


        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        String orderId = order.getId();
        orderApiController.deleteAllPizzasByOrderID(orderId);
        Assert.assertEquals(0, orderRepository.count());
    }


    /**
     * Testing the deleteAll method in the order repository
     */

    @Test
    public void deleteAll() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_S", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);

        MenuItem itemList1 = new MenuItem("Crust_L", 15.0, 250, false, "CRUST");
        MenuItem itemList2 = new MenuItem("topping_onions", 15.0, 250, false, "TOPPING");
        MenuItem itemList3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem itemList4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItems2InPizza = new ArrayList<>();
        menuItems2InPizza.add(itemList1);
        menuItems2InPizza.add(itemList2);
        menuItems2InPizza.add(itemList3);
        menuItems2InPizza.add(itemList4);


        Pizza pizza2 = new Pizza(menuItems2InPizza, PizzaSize.CRUST_LARGE, true);
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizzaList.add(pizza2);


        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);

        orderApiController.deleteAllCustomerOrders();
        Assert.assertEquals(0, orderRepository.count());
    }

    /**
     * Testing the getAllOrders method in the order API Controller
     */


    @Test
    public void getAllOrders() {
        Order order = new Order();
        orderRepository.save(order);
        orderApiController.getAllCustomersOrders();

        Assert.assertEquals("class Order {\n" +
                "    storeID: null\n" +
                "    pizzas: null\n" +
                "    totalPrice: null\n" +
                "}", order.toString());
    }

    /**
     * Testing the getOrderByID method in the order API Controller
     */


    @Test
    public void getByID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_M", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        String orderId = order.getId();
        orderApiController.getOrderByID(orderId);
        Assert.assertEquals(orderId, order.getId());
    }


    /**
     * Testing the getId method in the Order model class
     */


    @Test
    public void getAllPizzasByID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_L", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_mushroom", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        Order order = new Order(storeId, pizzaList);
        orderApiController.postCustomerOrder(order);
        order.getId();
        Assert.assertEquals("[class Pizza {\n" +
                "    pizzaId: null\n" +
                "    menuItems: [class MenuItem {\n" +
                "        menuItemID: null\n" +
                "        storeID: null\n" +
                "        menuItemID: null\n" +
                "        name: Crust_L\n" +
                "        price: 15.0\n" +
                "        calories: 250\n" +
                "        isGlutenFree: false\n" +
                "        menuItemType: CRUST\n" +
                "    }, class MenuItem {\n" +
                "        menuItemID: null\n" +
                "        storeID: null\n" +
                "        menuItemID: null\n" +
                "        name: topping_mushroom\n" +
                "        price: 15.0\n" +
                "        calories: 250\n" +
                "        isGlutenFree: false\n" +
                "        menuItemType: TOPPING\n" +
                "    }, class MenuItem {\n" +
                "        menuItemID: null\n" +
                "        storeID: null\n" +
                "        menuItemID: null\n" +
                "        name: SAUCE\n" +
                "        price: 8.0\n" +
                "        calories: 100\n" +
                "        isGlutenFree: false\n" +
                "        menuItemType: SAUCE\n" +
                "    }, class MenuItem {\n" +
                "        menuItemID: null\n" +
                "        storeID: null\n" +
                "        menuItemID: null\n" +
                "        name: CHEESE\n" +
                "        price: 9.0\n" +
                "        calories: 80\n" +
                "        isGlutenFree: false\n" +
                "        menuItemType: CHEESE\n" +
                "    }]\n" +
                "    pizzaSize: CRUST_SMALL\n" +
                "    pizzaCalories: 0\n" +
                "    pizzaPrice: 0.0\n" +
                "    isPizza: true\n" +
                "}]", pizzaList.toString());

    }

    /**
     * Testing the getByPizzaIDAndOrder method in the order repository
     * Will return a bad request
     */

    @Test
    public void getByPizzaIDAndOrderIDBadRequest() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("Crust_M", 15.0, 250, false, "CRUST");
        MenuItem menuItem2 = new MenuItem("topping_pepper", 15.0, 250, false, "TOPPING");
        MenuItem menuItem3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem menuItem4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);
        menuItemsInPizza.add(menuItem3);
        menuItemsInPizza.add(menuItem4);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        pizzaRepository.save(pizza);
        String pizza1Id = pizza.getPizzaId();

        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);


        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        String orderId = order.getId();
        Assert.assertEquals("<400 Bad Request,{}>", orderApiController.getPizzaByIDAndOrderID(orderId, pizza1Id).toString());

    }

    /**
     * Testing the getId method in the Order model class
     */

    @Test
    public void addPizzaByID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();
        MenuItem menuItem = new MenuItem("CRUST_S", 10.0, 250, true, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        String pizzaId = pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        String orderId = order.getId();
        orderApiController.addPizzaByIDAndOrderID(orderId, pizzaId);

        Assert.assertEquals(orderId, order.getId());
    }

    /**
     * Testing the deletePizzaByOrder method in the order API Controller
     */


    @Test
    public void deletePizza() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();
        MenuItem menuItem = new MenuItem("CRUST_M", 10.0, 250, true, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        String pizza1Id = pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        String orderId = order.getId();
        orderApiController.deletePizzaByOrder(orderId, pizza1Id);
        Assert.assertEquals(0, orderRepository.count());
    }

    /**
     * Testing the getStoreByIDAndOrderID method in the order API Controller
     */


    @Test
    public void getStoreByID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        String orderId = order.getId();

        orderApiController.getStoreByIDAndOrderID(orderId, storeId);
        Assert.assertEquals(storeId, store.getId());

    }

    /**
     * Testing the deleteStoreByIDAndOrderID method in the order API Controller
     */

    @Test
    public void deleteOrderFromStore() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        String orderId = order.getId();
        ResponseEntity<Void> delete = orderApiController.deleteStoreByIDAndOrderID(orderId, storeId);

        Assert.assertEquals("<200 OK,{}>", delete.toString());

    }


    /**
     * Testing the setTotalPriceForOwner method in the Order Model class
     */

    @Test
    public void ownerDiscount() {
        Order order = new Order();
        order.setTotalPriceForOwner("1234");
        Double priceOfPizza = 0.00;
        Assert.assertEquals(priceOfPizza, order.getTotalPrice());
    }

    /**
     * Testing the getStoreID method in the Order Model class
     */

    @Test
    public void getByStoreID() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);


        Assert.assertEquals(storeId, order.getStoreID());
    }


    /**
     * Testing the pizza method in the Order Model class to return the pizza List
     */

    @Test
    public void orderPizza() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);
        Order pizzaOrders = order.pizzas(pizzaList);
        Assert.assertEquals(pizzaOrders, order.pizzas(pizzaList));
    }


    /**
     * Testing the getTotalPrice method in the Order Model class
     */

    @Test
    public void totalPriceOfOrder() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);

        Double price = 19.0;
        Order totalPrice = order.totalPrice(19.0);
        Assert.assertEquals(price, totalPrice.getTotalPrice());
    }

    /**
     * Testing the addPizzasItem method in the Order Model class
     */

    @Test
    public void addPizza() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();

        MenuItem menuItem = new MenuItem("CRUST_L", 19.0, 350, false, "CRUST");
        MenuItem menuItem1 = new MenuItem("SAUCE", 7.0, 100, false, "SAUCE");
        MenuItem menuItem2 = new MenuItem("CHEESE", 19.0, 80, false, "CHEESE");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem1);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaRepository.save(pizza);
        pizza.getPizzaId();
        List<Pizza> pizzaList = new ArrayList<>();
        pizzaList.add(pizza);
        pizza.getPizzaId();

        Order order = new Order(storeId, pizzaList);
        orderRepository.save(order);

        MenuItem itemList1 = new MenuItem("Crust_L", 15.0, 250, false, "CRUST");
        MenuItem itemList2 = new MenuItem("topping_onions", 15.0, 250, false, "TOPPING");
        MenuItem itemList3 = new MenuItem("SAUCE", 8.0, 100, false, "SAUCE");
        MenuItem itemList4 = new MenuItem("CHEESE", 9.0, 80, false, "CHEESE");
        List<MenuItem> menuItems2InPizza = new ArrayList<>();
        menuItems2InPizza.add(itemList1);
        menuItems2InPizza.add(itemList2);
        menuItems2InPizza.add(itemList3);
        menuItems2InPizza.add(itemList4);


        Pizza pizza2 = new Pizza(menuItems2InPizza, PizzaSize.CRUST_LARGE, true);

        pizzaList.add(pizza2);
        Order addmorePizzas = order.addPizzasItem(pizza2);

        Assert.assertEquals(addmorePizzas, order);
    }

}
