package io.swagger.repository;

import io.swagger.api.MenuItemApiController;

import io.swagger.model.*;
import io.swagger.model.MenuItem;
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

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest(classes = {Swagger2SpringBoot.class})
public class MenuItemRepositoryTest {

    @Autowired
    private MenuItemRepository menuItemRepository;


    @Autowired
    private MenuItemApiController menuItemApiController;


    @Before
    public void setUp() {
        menuItemRepository.deleteAll();
    }

    @After
    public void tearDown() {
        menuItemRepository.deleteAll();
    }

    /**
     * Testing the post method in the menuItem repository
     * Making sure the menuItem is (created) saved in the menuItem repository
     */
    @Test
    public void createMenuItemTest() {
        MenuItem menuItem = new MenuItem();
        menuItemRepository.save(menuItem);
        long itemsInDtbase = menuItemRepository.count();
        assertEquals(1, itemsInDtbase);
    }

    /**
     * Testing the get method in the menuItem repository by finding the menuItem by Id
     * Making sure the menuItem is available in the menuItem repository
     */
    @Test
    public void getMenuItemByIdTest() {
        MenuItem menu = new MenuItem();
        MenuItem saveMenuItemInDtbase = menuItemRepository.save(menu);
        String id = menu.getID();
        MenuItem findMenuItem = menuItemRepository.findById(saveMenuItemInDtbase.getID());

        assertEquals(id, findMenuItem.getID());
    }

    /**
     * Testing the delete method for the menuItem repository
     */
    @Test
    public void deleteMenuItemByIdTest() {
        MenuItem menu = new MenuItem();
        menuItemRepository.save(menu);
        menuItemRepository.delete(menu.getID());

        assertEquals(0, menuItemRepository.count());
    }

    /**
     * Testing the deleteAll method for the menuItem repository
     */
    @Test
    public void deleteAllMenuItem() {
        MenuItem menu = new MenuItem();
        menuItemRepository.save(menu);
        MenuItem menu2 = new MenuItem();
        menuItemRepository.save(menu2);
        menuItemRepository.deleteAll();
        long count = menuItemRepository.count();
        assertEquals(0, count);
    }

    /**
     * Testing to get a list of menuItems using menuItem id
     * MenuItems should be saved in the menuItem repository for 1 menuItems
     */
    @Test
    public void getListOfMenuItemIdTest() {
        MenuItem menu = new MenuItem();
        menuItemRepository.save(menu);
        long count = menuItemRepository.count();
        assertEquals(1, count);
    }

    /**
     * Testing to get a list of menuItems using menuItem id
     * MenuItems should be saved in the menuItem repository for 2 menuItems
     */
    @Test
    public void getListOfMenuItemIdTest2() {
        MenuItem menu = new MenuItem();
        menuItemRepository.save(menu);
        MenuItem menu2 = new MenuItem();
        menuItemRepository.save(menu2);
        long count = menuItemRepository.count();
        assertEquals(2, count);
    }


    /**
     * Testing the menuItem ApiController using the post method to create a menuItem object
     */
    @Test
    public void menuItemControllerCreateMethod() {

        MenuItem menuItem = new MenuItem("cheese", 15.0, 120, false, "CHEESE");
        menuItemApiController.createMenuItem(menuItem);
        Assert.assertEquals(1, menuItemRepository.count());
    }


    /**
     * Testing the menuItem ApiController using the delete method to delete a menuItem
     */
    @Test
    public void menuItemControllerDeleterMethod() {

        MenuItem menuItem = new MenuItem("crust", 10.0, 130, true, "crust");

        menuItemApiController.createMenuItem(menuItem);
        String id = menuItem.getID();
        menuItemApiController.deleteMenuItemById(id);
        Assert.assertEquals(0, menuItemRepository.count());

    }

    /**
     * Testing the menuItem ApiController using the get method to get all menuItems from menuItem repository
     */
    @Test
    public void menuItemControllerGetAllMethod() {
        MenuItem menuItem = new MenuItem("regularCrust", 1.50, 120, false, "CRUST_SMALL");
        ResponseEntity<MenuItem> menuItem1 = menuItemApiController.createMenuItem(menuItem);
        ResponseEntity<List<MenuItem>> menuItemList = menuItemApiController.getMenuItems();

        Assert.assertEquals(1, menuItemRepository.count());

    }

    /**
     * Testing the getCalories method in the MenuItem model class
     */

    @Test
    public void menuGetCalories() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemApiController.createMenuItem(menuItem);

        assertEquals("80", menuItem.getCalories().toString());

    }

    /**
     * Testing the getPrice method in the MenuItem model class
     */
    @Test
    public void menuGetPrice() {
        MenuItem menuItem = new MenuItem("CRUST_M", 19.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        menuItemApiController.createMenuItem(menuItem);
        assertEquals("19.0", menuItem.getPrice().toString());
    }

    /**
     * Testing the menuItem type method in the MenuItemType model class
     */

    @Test
    public void menuItemType() {
        MenuItem menuItem = new MenuItem("Topping_ONIONS    ", 19.0, 80, false, "TOPPING_ONIONS");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemApiController.createMenuItem(menuItem);

        assertEquals("TOPPING_ONIONS", menuItem.getMenuItemType().toString());
    }


    /**
     * Testing the isGlutenFree method in the MenuItem model class
     */
    @Test
    public void menuGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 80, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemApiController.createMenuItem(menuItem);

        assertFalse("false", menuItem.isIsGlutenFree());

    }

    /**
     * Testing the getName method in the MenuItem model class
     */
    @Test
    public void menuItemName() {
        MenuItem menuItem = new MenuItem("Topping_PEPPERONI", 19.0, 80, false, "TOPPING");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemApiController.createMenuItem(menuItem);

        assertEquals("Topping_PEPPERONI", menuItem.getName());
    }

    /**
     * Testing the getStoreID method in the MenuItem model class
     */

    @Test
    public void menuItemStoreId() {
        MenuItem menuItem = new MenuItem();
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemRepository.save(menuItem);

        assertEquals(null, menuItem.getStoreID());
    }

    /**
     * Testing the getMenuItemById method in the menuItem API Controller class
     */

    @Test
    public void menuItemById() {
        MenuItem menuItem = new MenuItem();
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);

        menuItemApiController.createMenuItem(menuItem);
        String menuItemID = menuItem.getID();
        menuItemApiController.getMenuItemById(menuItemID);
        assertEquals(menuItemID, menuItem.getID());

    }

    /**
     * Testing the setStoreId method in the MenuItem model class
     */

    @Test
    public void menuItemSetStoreId() {
        Store store = new Store();
        String storeId = store.getId();
        MenuItem menuItem = new MenuItem();
        menuItem.setStoreID(storeId);
        assertEquals(storeId, store.getId());
    }

    /**
     * Testing the setName method in the MenuItem model class
     */

    @Test
    public void menuItemSetName() {
        MenuItem menuItem = new MenuItem();
        MenuItem name = menuItem.name("SAUCE");
        assertEquals("SAUCE", name.getName());

    }

    /**
     * Testing the setName method in the MenuItem model class
     * Name of the topping
     */
    @Test
    public void menuItemSetName2() {
        MenuItem menuItem = new MenuItem();
        menuItem.setName("CHEESE");
        assertEquals("CHEESE", menuItem.getName());
    }


    /**
     * Testing the setPrice method in the MenuItem model class
     */
    @Test
    public void menuItemSetPrice() {

        MenuItem menuItemsInPizza = new MenuItem();
        MenuItem price = menuItemsInPizza.price(12.0);
        Double priceOfItem = 12.0;
        assertEquals(priceOfItem, price.getPrice());
    }


    /**
     * Testing the getPrice method in the MenuItem model class for topping
     */

    @Test
    public void toppingMenuItemPrice() {
        MenuItem menuItem = new MenuItem("TOPPING", 2.0, 50, false, "TOPPING");
        menuItemRepository.save(menuItem);
        menuItem.getMenuItemType();
        Double price = 2.0;
        assertEquals(price, menuItem.getPrice());

    }

    /**
     * Testing the setGlutenFree method in the MenuItem model class
     */

    @Test
    public void isGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_M", 10.0, 200, true, "CRUST_M");
        Boolean glutenFree = menuItem.isIsGlutenFree();
        menuItem.setIsGlutenFree(glutenFree);
        assertTrue("gluten free", true);
    }

    /**
     * Testing the setMenuItemType method in the MenuItem model class
     */

    @Test
    public void setMenuItemType() {
        MenuItem menuItem = new MenuItem("CRUST_M", 10.0, 200, true, "CRUST_M");
        menuItem.getMenuItemType();
        menuItem.setMenuItemType("CRUST_M");
        assertEquals("CRUST_M", menuItem.getMenuItemType());
    }


    /**
     * Testing the setPrice method in the MenuItem model class with the menuItem type
     */
    @Test
    public void setPriceOfMenuItem() {
        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemType("");
        menuItem.setPrice();
        Double priceOfItem = menuItem.getPrice();
        Double expected = 0.00;
        Assert.assertEquals(expected, priceOfItem);
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     */
    @Test
    public void setCaloriesOfMenuItem() {
        MenuItem menuItem = new MenuItem();
        menuItem.setMenuItemType("");
        menuItem.setCalories(185);
        Integer expected = 185;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the getCalories method in the MenuItem model class with the menuItem type
     */
    @Test
    public void menuItemCalories() {
        MenuItem menuItem = new MenuItem();
        MenuItem calories = menuItem.calories(100);
        Integer expected = 100;
        Assert.assertEquals(expected, calories.getCalories());
    }

    /**
     * Testing the isGluten method in the MenuItem model class with the menuItem type
     */
    @Test
    public void glutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_S", 10.0, 200, true, "CRUST_S");
        MenuItem isGluten = menuItem.isGlutenFree(true);
        Assert.assertTrue("crust is gluten free", isGluten.isIsGlutenFree());
    }


    /**
     * Testing the getMenuItemType method in the MenuItem model class with the menuItem type
     */
    @Test
    public void typeOfMenuItem() {
        MenuItem menuItem = new MenuItem("CRUST_M", 10.0, 200, true, "CRUST_M");
        MenuItem type = menuItem.menuItemType("CRUST_M");
        Assert.assertEquals("CRUST_M", type.getMenuItemType());
    }

    /**
     * Testing the valueOf method in the MenuItem model class
     */

    @Test
    public void valueOfMenuItem() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 200, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType getType = MenuItemType.CRUST_M;
        Object typeOfMenuItem = menuItem.getMenuItemType();
        MenuItemType.fromValue("CRUST");
        Assert.assertEquals(getType.toString(), typeOfMenuItem.toString());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is topping
     */

    @Test
    public void caloriesTopping() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 200, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.TOPPING;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(100);
        Integer expected = 100;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is small crust, not gluten free
     */

    @Test
    public void caloriesCrustSmallNotGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_S", 9.0, 200, false, "CRUST_S");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_S;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(185);
        Integer expected = 185;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is medium crust, not gluten free
     */

    @Test
    public void caloriesCrustMediumNotGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_M", 9.0, 200, false, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_M;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(245);
        Integer expected = 245;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is large crust, not gluten free
     */
    @Test
    public void caloriesCrustLargeNotGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_L", 9.0, 200, false, "CRUST_L");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_L;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(325);
        Integer expected = 325;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is large crust, gluten free
     */

    @Test
    public void caloriesCrustLargeGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_L", 9.0, 200, true, "CRUST_L");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_L;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(275);
        Integer expected = 275;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is medium crust, gluten free
     */

    @Test
    public void caloriesCrustMediumGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_M", 10.0, 200, true, "CRUST_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_M;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(200);
        Integer expected = 200;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is small crust, gluten free
     */

    @Test
    public void caloriesCrustSmallGlutenFree() {
        MenuItem menuItem = new MenuItem("CRUST_S", 10.0, 200, true, "CRUST_S");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_S;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(130);
        Integer expected = 130;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is cheese
     */
    @Test
    public void caloriesCheese() {
        MenuItem menuItem = new MenuItem("Cheese", 10.0, 200, false, "Cheese");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CHEESE;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(80);
        Integer expected = 80;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setCalories method in the MenuItem model class with the menuItem type
     * MenuItem is sauce
     */
    @Test
    public void caloriesSauce() {
        MenuItem menuItem = new MenuItem("Sauce", 7.0, 200, false, "Sauce");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.SAUCE;
        menuItem.setMenuItemType(type);
        menuItem.setCalories(75);
        Integer expected = 75;
        Assert.assertEquals(expected, menuItem.getCalories());
    }

    /**
     * Testing the setPrice method in the MenuItem model class with the menuItem type
     * MenuItem is topping
     */
    @Test
    public void priceOfTopping() {
        MenuItem menuItem = new MenuItem("Topping", 7.0, 200, false, "Topping");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.TOPPING;
        menuItem.setMenuItemType(type);
        menuItem.setPrice();
        Double expected = 2.00;
        Assert.assertEquals(expected, menuItem.getPrice());
    }

    /**
     * Testing the setPrice method in the MenuItem model class with the menuItem type
     * MenuItem is small crust, not gluten free
     */

    @Test
    public void priceOfCrustSmall() {
        MenuItem menuItem = new MenuItem("Crust_S", 7.0, 200, false, "Crust_S");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_S;
        menuItem.setMenuItemType(type);
        menuItem.setPrice();
        Double expected = 8.00;
        Assert.assertEquals(expected, menuItem.getPrice());
    }

    /**
     * Testing the setPrice method in the MenuItem model class with the menuItem type
     * MenuItem is medium crust, not gluten free
     */
    @Test
    public void priceOfCrustMedium() {
        MenuItem menuItem = new MenuItem("Crust_M", 7.0, 200, false, "Crust_M");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_M;
        menuItem.setMenuItemType(type);
        menuItem.setPrice();
        Double expected = 10.00;
        Assert.assertEquals(expected, menuItem.getPrice());
    }

    /**
     * Testing the setPrice method in the MenuItem model class with the menuItem type
     * MenuItem is large crust, not gluten free
     */
    @Test
    public void priceOfCrustLarge() {
        MenuItem menuItem = new MenuItem("Crust_L", 7.0, 200, false, "Crust_L");
        List<MenuItem> menuItemsInPizza = new ArrayList<>();
        menuItemsInPizza.add(menuItem);
        MenuItemType type = MenuItemType.CRUST_L;
        menuItem.setMenuItemType(type);
        menuItem.setPrice();
        Double expected = 12.00;
        Assert.assertEquals(expected, menuItem.getPrice());
    }

}

