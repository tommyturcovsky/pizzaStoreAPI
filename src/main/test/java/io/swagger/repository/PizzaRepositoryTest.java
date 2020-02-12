package io.swagger.repository;

import io.swagger.api.PizzaApiController;
import io.swagger.model.MenuItem;

import io.swagger.model.Pizza;
import io.swagger.model.PizzaSize;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.Swagger2SpringBoot;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest(classes = {Swagger2SpringBoot.class})
public class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaApiController pizzaApiController;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {
        pizzaRepository.deleteAll();
    }

    @After
    public void tearDown() {
        pizzaRepository.deleteAll();
    }


    /**
     * Testing the create method for the pizza repository
     */

    @Test
    public void createPizzaTest() {
        Pizza pizza = new Pizza();
        Pizza savePizza = pizzaRepository.save(pizza);
        long pizzaCount = pizzaRepository.count();
        assertEquals(1, pizzaCount);
    }

    /**
     * Testing the get method for the pizza repository
     * Getting the pizza by Id method
     */
    @Test
    public void getPizzaByIdTest() {
        Pizza pizza = new Pizza();
        Pizza savePizzaInDtbase = pizzaRepository.save(pizza);
        String id = pizza.getPizzaId();
        Pizza findPizza = pizzaRepository.findById(savePizzaInDtbase.getPizzaId());

        assertEquals(id, findPizza.getPizzaId());
    }


    /**
     * Testing the delete method for the pizza repository
     * deleting the pizza by Id
     */
    @Test
    public void deletePizzaByIdTest() {
        Pizza pizza = new Pizza();
        Pizza savePizza = pizzaRepository.save(pizza);
        pizza.getPizzaId();
        pizzaRepository.delete(savePizza.getPizzaId());

        assertEquals(0, pizzaRepository.count());
    }

    /**
     * Testing the deleteAll method for the pizza repository
     */
    @Test
    public void deleteAllPizzas() {
        Pizza pizza1 = new Pizza();
        pizzaRepository.save(pizza1);
        Pizza pizza2 = new Pizza();
        pizzaRepository.save(pizza2);
        pizzaRepository.deleteAll();
        long count = pizzaRepository.count();
        assertEquals(0, count);
    }

    /**
     * Testing to get a list of menuItems using pizza id
     * Pizza should be saved in the pizza repository for 1 pizza
     */
    @Test
    public void getListOPizzaIdTest() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        long count = pizzaRepository.count();
        assertEquals(1, count);
    }

    /**
     * Testing the pizza ApiController using the post method to create a pizza object in pizza repository
     */
    @Test
    public void pizzaControllerCreateMethod() {
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizzaApiController.createPizza(pizza);
        Assert.assertEquals(1, pizzaRepository.count());
    }

    /**
     * Testing the pizza ApiController using the post method to create a pizza object in pizza repository
     * with attributes.
     */
    @Test
    public void pizzaControllerCreateMethod2() {
        MenuItem menuItem = new MenuItem("allToppings", 20.00, 400, false, "TOPPING");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_LARGE, true);
        pizzaApiController.createPizza(pizza);

        String id = pizza.getPizzaId();
        Pizza pizzaStored = pizzaRepository.findById(id);
        Assert.assertEquals(pizzaStored.toString(), pizza.toString());
    }


    /**
     * Testing the delete ApiController using the delete method to delete a pizza object
     */
    @Test
    public void pizzaControllerDeleterMethod() {

        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_LARGE, true);
        pizzaApiController.createPizza(pizza);
        String id = pizza.getPizzaId();
        pizzaApiController.deletePizzaByID(id);
        Assert.assertEquals(0, pizzaRepository.count());

    }


    /**
     * Testing the pizza ApiController using the get method to get all pizza from pizza repository
     */
    @Test
    public void pizzaControllerGetAllMethod() {
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaApiController.createPizza(pizza);
        pizzaApiController.getAllPizzas();

        Assert.assertEquals(1, pizzaRepository.count());
    }


    /**
     * Testing the update method for the pizza repository
     * Updating the method by the Id
     */

    @Test
    public void updatePizzaId() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        String id = pizza.getPizzaId();
        pizzaApiController.getPizzaByID(id);
        Pizza findpizza = pizzaRepository.findById(id);
        Assert.assertEquals(id, findpizza.getPizzaId());
    }


    /**
     * Testing the update method for the pizza repository
     * Updating the pizza with a new menu item. Adding menu Item to the pizza with the pizza Id
     */
    @Test
    public void updatePizzaIdAndMenuItemId() {
        Pizza pizza = new Pizza();
        Pizza pizzaInData = pizzaRepository.save(pizza);
        MenuItem menu = new MenuItem("crust", 3.00, 150, true, "CRUST_S");
        menuItemRepository.save(menu);
        String menuId = menu.getID();
        MenuItem addMenu = menuItemRepository.findById(menuId);
        Pizza update = pizza.addMenuItemItem(addMenu);


        Assert.assertEquals(pizzaInData, update);
    }


    /**
     * Testing the get all method in the pizza API Controller
     */

    @Test
    public void pizzaControllerGetAll() {
        MenuItem menuItem = new MenuItem("Crust_S", 20.00, 400, false, "CRUST_S");
        MenuItem menuItem2 = new MenuItem("Sauce", 10.00, 100, false, "TOPPING");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemsInPizza.add(menuItem2);

        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        pizzaApiController.createPizza(pizza);
        pizzaApiController.getAllPizzas();

        String id = pizza.getPizzaId();
        Pizza pizzaStored = pizzaRepository.findById(id);
        Assert.assertEquals(pizzaStored.toString(), pizza.toString());
    }


    /**
     * Testing the get method by Pizza Id in the pizza API Controller
     */

    @Test
    public void pizzaControllerGetPizzaByID() {
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
        pizzaApiController.createPizza(pizza);

        String id = pizza.getPizzaId();
        pizzaApiController.getPizzaByID(id);

        Pizza pizzaInRepository = pizzaRepository.findById(id);

        Assert.assertEquals(id, pizzaInRepository.getPizzaId());
    }

    /**
     * Testing pizzaSize method in the Pizza model class class
     */

    @Test
    public void pizzaSize() {
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_LARGE, false);
        pizzaRepository.save(pizza);
        Assert.assertEquals("CRUST_LARGE", PizzaSize.CRUST_LARGE.toString());
    }


    /**
     * Testing the getMenu method in the Pizza model class
     */

    @Test
    public void pizzaGetMenu() {
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        assertEquals(menuItemsInPizza, pizza.getMenuItems());
    }


    /**
     * Testing the getCalories method in the Pizza model class
     */

    @Test
    public void pizzaGetCalories() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizza.setPizzaCalories();
        pizzaApiController.createPizza(pizza);

        assertEquals(menuItem.getCalories(), pizza.getPizzaCalories());

    }


    /**
     * Testing the getPrices method in the Pizza model class
     */
    @Test
    public void pizzaGetPrice() {
        MenuItem menuItem = new MenuItem("CRUST_M", 19.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizza.setPizzaCalories();
        pizzaApiController.createPizza(pizza);

        assertEquals(menuItem.getPrice(), pizza.getPizzaPrice());

    }

    /**
     * Testing the getSize method in the Pizza model class
     * Method will return the size of the Pizza
     */
    @Test
    public void pizzaGetSize() {
        MenuItem menuItem = new MenuItem("CRUST_M", 19.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizzaApiController.createPizza(pizza);

        assertEquals("CRUST_MEDIUM", pizza.getPizzaSize().toString());
    }


    /**
     * Testing the Menu method in the Pizza model class
     * Method will return a list of the menuItems that are in that Pizza object
     */

    @Test
    public void pizzaMenu() {
        MenuItem menuItem = new MenuItem("CRUST_M", 19.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizzaApiController.createPizza(pizza);

        assertEquals("[class MenuItem {\n" +
                "    menuItemID: null\n" +
                "    storeID: null\n" +
                "    menuItemID: null\n" +
                "    name: CRUST_M\n" +
                "    price: 19.0\n" +
                "    calories: 80\n" +
                "    isGlutenFree: false\n" +
                "    menuItemType: CRUST_M\n" +
                "}]", pizza.getMenuItems().toString());
    }


    /**
     * Testing the setCalories method in the Pizza model class
     */

    @Test
    public void pizzaSetCalories() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 90, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        pizzaApiController.createPizza(pizza);
        pizza.setPizzaCalories();
        Integer calories = 90;
        assertEquals(calories, pizza.getPizzaCalories());

    }

    /**
     * Testing the setPrice method in the Pizza model class
     */


    @Test
    public void pizzaSetPrice() {
        MenuItem menuItem = new MenuItem("CRUST_M", 39.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);

        pizzaApiController.createPizza(pizza);

        assertEquals(pizza.getPizzaPrice(), pizza.setPizzaPrice());

    }

    /**
     * Testing the getMenuItemsByPizzaID method in the Pizza model class
     */


    @Test
    public void getMenuItemsByPizzaID() {
        MenuItem menuItem = new MenuItem("CRUST_S", 39.0, 80, false, "CRUST_S");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_SMALL, true);
        pizzaApiController.createPizza(pizza);
        String id = pizza.getPizzaId();
        assertEquals("<200 OK,[class MenuItem {\n" +
                "    menuItemID: null\n" +
                "    storeID: null\n" +
                "    menuItemID: null\n" +
                "    name: CRUST_S\n" +
                "    price: 39.0\n" +
                "    calories: 80\n" +
                "    isGlutenFree: false\n" +
                "    menuItemType: CRUST_S\n" +
                "}],{}>", pizzaApiController.getMenuItemsByPizzaID(id).toString());

    }

    /**
     * Testing the updateMenuItemsByPizzaID method in the Pizza model class
     */


    @Test
    public void updateMenuItemByPizzaID() {
        MenuItem menuItem = new MenuItem("CRUST_L", 23.0, 100, true, "CRUST_L");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemRepository.save(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_LARGE, true);
        pizzaRepository.save(pizza);
        String pizzaID = pizza.getPizzaId();
        String menuItemID = menuItem.getID();
        pizzaApiController.updateMenuItemByIDAndPizzaID(pizzaID, menuItemID);

        assertEquals(pizza.getPizzaId(), pizzaID);
    }


    /**
     * Testing the isPizza method in the Pizza model class
     */
    @Test
    public void setPizza() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        Boolean isPizza = pizza.setIsPizza();
        pizza.isIsPizza();
        assertTrue("It is a complete pizza", isPizza);
    }


    /**
     * Testing the valueOf method in the Pizza model class
     */

    @Test
    public void valueOfPizza() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 200, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        PizzaSize getSize = PizzaSize.CRUST_MEDIUM;
        PizzaSize.fromValue("Medium Pizza");
        Assert.assertEquals(getSize, pizza.getPizzaSize());
    }


    /**
     * Testing the menuItems method in the Pizza model class
     * Method will return the menuItems in the pizza
     */

    @Test
    public void listOfMenuItem() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 200, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        Pizza pizza = new Pizza(menuItemsInPizza, PizzaSize.CRUST_MEDIUM, true);
        Pizza list = pizza.menuItem(menuItemsInPizza);
        Assert.assertEquals(menuItemsInPizza, list.getMenuItems());

    }

    /**
     * Testing the pizzPrice method in the Pizza model class
     */

    @Test
    public void priceOfPizza() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        pizza.pizzaPrice(10.00);
        Double expected = 10.00;
        Assert.assertEquals(expected, pizza.getPizzaPrice());
    }

    /**
     * Testing the isCompletePizza method in the Pizza model class
     */

    @Test
    public void completePizza() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        pizza.isPizza(true);
        Boolean isCompletePizza = true;
        Assert.assertEquals(isCompletePizza, pizza.isIsPizza());
    }

    /**
     * Testing the pizzaSize method in the PizzaSize model class
     */

    @Test
    public void sizeOfPizza() {
        Pizza pizza = new Pizza();
        pizzaRepository.save(pizza);
        PizzaSize size = PizzaSize.CRUST_SMALL;
        pizza.setPizzaSize(size);
        Assert.assertEquals(size, pizza.getPizzaSize());
    }

}
