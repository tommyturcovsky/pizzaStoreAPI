package io.swagger.repository;


import io.swagger.api.StoreApiController;
import io.swagger.model.MenuItem;
import io.swagger.model.MenuItemType;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Store;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest(classes = {Swagger2SpringBoot.class})
public class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private StoreApiController storeApiController;

    @Before
    public void setUp() {
        storeRepository.deleteAll();
        menuItemRepository.deleteAll();
    }

    @After
    public void tearDown() {
        storeRepository.deleteAll();
        menuItemRepository.deleteAll();
    }

    /**
     * Testing findById method in the store repository
     */
    @Test
    public void getStoreByIdTest() {
        Store store = new Store();
        Store actual = storeRepository.save(store);
        Store result = storeRepository.findById(store.getId());
        assertEquals(result, actual);
    }


    /**
     * Testing Post, Put, Post to clear Entities in DB w/ new value, then delete all
     * <p>
     * Methods Tested
     * StoreApiController: addNewStorePost, addNewStorePut, addNewStore, deleteAllStores
     */
    @Test
    public void storeControllerMethod() {
        List<MenuItem> items = new ArrayList<>();
        Store store1 = new Store("store1", "address", true, items);
        Store store2 = new Store("store2", "address", true, items);

        assertEquals(0, storeRepository.count());
        storeApiController.addNewStorePost(store1);
        assertEquals(1, storeRepository.count());
        storeApiController.addNewStorePut(store2);
        assertEquals(2, storeRepository.count());
        storeApiController.addNewStorePost(store2);
        assertEquals(1, storeRepository.count());
        storeApiController.deleteAllStores();
        assertEquals(0, storeRepository.count());

        assertNotNull(storeApiController.getAllLocations());
        new Store("store2", "address", true, items);

    }

    /**
     * Testing the store getters and setters method
     */
    @Test
    public void storeModelGetterAndSetters() {
        Store store = new Store();
        store.setStoreName("StoreName");
        assertEquals("StoreName", store.getStoreName());

        store.setAddress("123 Easy Street");
        assertEquals("123 Easy Street", store.getAddress());

        store.setId("123abc");
        assertEquals("123abc", store.getId());

        store.setGlutenFree(false);
        assertEquals(false, store.isGlutenFree());

        List<MenuItem> menuItemList = new ArrayList<>();
        store.setStoreMenu(menuItemList);
        System.out.println(store.getStoreMenu().toString());
        assertEquals(0, store.getStoreMenu().size());

        MenuItem menuItem = new MenuItem();
        store.addStoreMenuItem(menuItem);
        assertEquals(1, store.getStoreMenu().size());

        Store storeCopy = store;
        Store notEqualStore = new Store();
        assertTrue(store.equals(storeCopy));
        assertFalse(store.equals(notEqualStore));

        assertEquals("class Store {\n" +
                        "    id: 123abc\n" +
                        "    storeName: StoreName\n" +
                        "    address: 123 Easy Street\n" +
                        "    glutenFree: false\n" +
                        "    storeMenu: [class MenuItem {\n" +
                        "        menuItemID: null\n" +
                        "        storeID: null\n" +
                        "        menuItemID: null\n" +
                        "        name: null\n" +
                        "        price: null\n" +
                        "        calories: null\n" +
                        "        isGlutenFree: false\n" +
                        "        menuItemType: null\n" +
                        "    }]\n" +
                        "}",
                store.toString());
    }

    /**
     * Testing to get the storeByID
     */
    @Test
    public void getStoreByStoreIDTest() {
        Store store = new Store();
        Store storeInDb = storeRepository.save(store);
        String storeId = store.getId();
        ResponseEntity<Store> createdStore = storeApiController.getStoreById(storeId);
        assertEquals(store, storeInDb);
    }

    /**
     * Testing to delete store by store Id
     */

    @Test
    public void deleteStoreByStoreIDTest() {
        Store store = new Store();
        storeRepository.save(store);
        String storeId = store.getId();
        storeApiController.deleteStoreById(storeId);

        assertEquals(0, storeRepository.count());
    }


    /**
     * Testing to addMenuItemByStoreId
     */

    @Test
    public void addMenuItemByStoreIdTest() {
        MenuItem smallCrust = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        menuItemRepository.save(smallCrust);
        List<MenuItem> itemsForMainStore = new ArrayList<>();

        Store mainStore = new Store("Main Location", "6010 15th Ave NW Seattle, WA 98107 United States", false, itemsForMainStore);
        Store storeInDb = storeRepository.save(mainStore);

        String storeId = storeInDb.getId();
        String smallCrustId = smallCrust.getID();
        storeApiController.addMenuItemByIDAndStoreID(storeId, smallCrustId);

        assertEquals(1, menuItemRepository.count());
    }

    /**
     * Testing to deleteMenuItemByStore
     */

    @Test
    public void deleteMenuItemByStoreTest() {
        MenuItem smallCrust = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        menuItemRepository.save(smallCrust);
        List<MenuItem> itemsForMainStore = new ArrayList<>();

        Store mainStore = new Store("Main Location", "6010 15th Ave NW Seattle, WA 98107 United States", false, itemsForMainStore);
        Store storeInDb = storeRepository.save(mainStore);

        String storeId = storeInDb.getId();
        String smallCrustId = smallCrust.getID();
        storeApiController.deleteMenuItemByStore(storeId, smallCrustId);

        int countOfMenuItems = itemsForMainStore.size();

        assertEquals(0, countOfMenuItems);
    }

    /**
     * Testing to getMenuItemByIDAndStoreID
     */


    @Test
    public void getMenuItemByIDAndStoreIDTest() {
        MenuItem smallCrust = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        menuItemRepository.save(smallCrust);

        String menuItemId = smallCrust.getID();

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(smallCrust);

        Store store = new Store("Main Location", "6010 15th Ave NW Seattle, WA 98107 United States", false, menuItems);
        storeRepository.save(store);

        String storeId = store.getId();

        ResponseEntity<MenuItem> menuItemInStore = storeApiController.getMenuItemByIDAndStoreID(storeId, menuItemId);
        assertEquals(smallCrust.getID(), menuItemInStore.getBody().getID());
    }

    /**
     * Testing to get store Id
     */

    @Test
    public void storeIdTest() {
        Store store = new Store();
        Store storeWithId = store.storeID("84857");
        assertEquals(store, storeWithId);
    }


    /**
     * Testing to get store name
     */

    @Test
    public void storeNameTest() {
        Store store = new Store();
        Store storeWithName = store.storeName("Main Location");
        assertEquals(store, storeWithName);
    }

    /**
     * Testing to get store address
     */

    @Test
    public void storeAddressTest() {
        Store store = new Store();
        Store storeWithAddress = store.storeID("123 Main Street");
        assertEquals(store, storeWithAddress);
    }

    /**
     * Testing to get store address
     */

    @Test
    public void storeGlutenFreeTest() {
        Store store = new Store();
        Store storeWithGluten = store.glutenFree(true);
        assertEquals(store, storeWithGluten);
    }

    /**
     * Testing to add a menu to a store
     */


    @Test
    public void addStoreMenuTest() {
        Store store = new Store();
        MenuItem smallCrust = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(smallCrust);
        Store storeWithMenu = store.addStoreMenuItem(smallCrust);
        assertEquals(store, storeWithMenu);
    }

    /**
     * Testing to add a menuItems to list
     */

    @Test
    public void storeMenuListTest() {
        Store store = new Store();
        MenuItem smallCrust = new MenuItem("Small Crust", 8.00, 100, false, MenuItemType.CRUST_S);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(smallCrust);
        Store storeWithMenu = store.storeMenu(menuItems);
        assertEquals(store, storeWithMenu);
    }

}




