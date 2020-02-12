package io.swagger.repository;


import io.swagger.model.*;
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
import io.swagger.api.ReceiptApiController;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application-test.properties")
@SpringBootTest(classes = {Swagger2SpringBoot.class})
public class ReceiptRepositoryTest {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptApiController receiptApiController;

    @Autowired
    private OrderRepository orderRepository;


    @Before
    public void setUp() {
        receiptRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @After
    public void tearDown() {
        receiptRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void receiptModelGetterAndSetters() {
        Receipt receipt = new Receipt();
        receipt.setCreditCardNum("0000111122223333");
        assertEquals("0000111122223333", receipt.getCreditCardNum());

        receipt.setCreditCardMonthExp("02");
        assertEquals("02", receipt.getCreditCardMonthExp());

        receipt.setCreditCardYearExp("21");
        assertEquals("21", receipt.getCreditCardYearExp());

        receipt.setId("123abc");
        assertEquals("123abc", receipt.getId());


        receipt.setOrderId("orderId");
        assertEquals("orderId", receipt.getOrderId());

        Receipt receiptCopy = receipt;
        Receipt notEqualReceipt = new Receipt();
        assertTrue(receipt.equals(receiptCopy));
        assertFalse(receipt.equals(notEqualReceipt));

        assertEquals("class Receipt {\n" +
                "    receiptId: 123abc\n" +
                "    creditCardNum: 0000111122223333\n" +
                "    creditCardMonthExp: 02\n" +
                "    creditCardYearExp: 21\n" +
                "    orderId: orderId\n" +
                "    totalPrice: 0.0\n" +
                "}", receipt.toString());
    }

    /**
     * Testing the receipt creditCardYear method to get the credit card expiration year from a receipt object
     */
    @Test
    public void receiptGetterCreditCardYearExpTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);
        receiptApiController.addReceiptPost(receipt);

        Assert.assertEquals("20", receipt.getCreditCardYearExp());
    }

    /**
     * Testing the post method in the receipt repository
     * Making sure the receipt is (created) saved in the receipt repository
     */
    @Test
    public void createReceiptTest() {
        Receipt receipt = new Receipt();
        receiptRepository.save(receipt);
        long receiptsInDatabase = receiptRepository.count();
        assertEquals(1, receiptsInDatabase);
    }

    /**
     * Testing the get method in the receipt repository by finding the receipt by Id
     * Making sure the receipt is available in the receipt repository
     */
    @Test
    public void getReceiptByIdTest() {
        Receipt receipt = new Receipt();
        Receipt saveReceiptInDatabase = receiptRepository.save(receipt);
        String id = receipt.getId();
        Receipt findReceipt = receiptRepository.findById(saveReceiptInDatabase.getId());

        assertEquals(id, findReceipt.getId());
    }

    /**
     * Testing the delete by id method for the receipt repository
     */
    @Test
    public void deleteReceiptByIdTest() {
        Receipt receipt = new Receipt();
        receiptRepository.save(receipt);
        receiptRepository.delete(receipt.getId());

        assertEquals(0, receiptRepository.count());
    }

    /**
     * Testing the deleteAll method for the receipt repository
     */
    @Test
    public void deleteAllReceiptsTest() {
        Receipt receipt = new Receipt();
        receiptRepository.save(receipt);
        Receipt receipt2 = new Receipt();
        receiptRepository.save(receipt2);
        receiptRepository.deleteAll();
        long count = receiptRepository.count();
        assertEquals(0, count);
    }


    /**
     * Testing the receipt ApiController using the post method to create a receipt object
     */
    @Test
    public void receiptControllerPostTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);
        receiptApiController.addReceiptPost(receipt);
        Assert.assertEquals(1, receiptRepository.count());
    }

    /**
     * Testing the receipt ApiController using the post method to create a receipt object
     */
    @Test
    public void receiptControllerPutTest() {
        Order order1 = new Order();
        orderRepository.save(order1);
        String order1Id = order1.getId();
        Receipt receipt1 = new Receipt("0000111122223333", "02", "20", order1Id);

        Order order2 = new Order();
        orderRepository.save(order2);
        String order2Id = order2.getId();
        Receipt receipt2 = new Receipt("0000111122256333", "05", "22", order2Id);

        receiptApiController.addReceiptPost(receipt1);
        receiptApiController.addReceiptPut(receipt2);

        Assert.assertEquals(2, receiptRepository.count());
    }


    /**
     * Testing the receipt ApiController using the deleteAll method to delete 2 receipts
     */
    @Test
    public void receiptControllerDeleteAllReceiptsTest() {

        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();
        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);

        Receipt receipt2 = new Receipt();

        receiptApiController.addReceiptPost(receipt);
        receiptApiController.addReceiptPost(receipt2);

        receiptApiController.deleteAllReceipts();

        Assert.assertEquals(0, receiptRepository.count());

    }

    /**
     * Testing the receipt ApiController using the delete method to delete a receipt
     */
    @Test
    public void receiptControllerDeleteByIdTest() {

        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();
        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);

        receiptApiController.addReceiptPost(receipt);
        String id = receipt.getId();
        receiptApiController.deleteReceiptById(id);
        Assert.assertEquals(0, receiptRepository.count());

    }

    /**
     * Testing the receipt ApiController using the getAll method to get 2 saved receipts
     */
    @Test
    public void receiptControllerGetAllReceiptsTest() {

        Order order1 = new Order();
        orderRepository.save(order1);
        String orderId = order1.getId();
        Receipt receipt1 = new Receipt("0000111122223333", "02", "20", orderId);

        Order order2 = new Order();
        orderRepository.save(order2);
        String order2Id = order2.getId();
        Receipt receipt2 = new Receipt("0000111122256333", "05", "22", order2Id);

        receiptApiController.addReceiptPost(receipt1);
        receiptApiController.addReceiptPut(receipt2);

        receiptApiController.getAllReceipts();

        Assert.assertEquals(2, receiptRepository.count());

    }


    /**
     * Testing the receipt ApiController using the get by id method to return a receipt
     */
    @Test
    public void receiptControllerGetByIdTest() {

        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();
        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);

        receiptApiController.addReceiptPost(receipt);
        String id = receipt.getId();
        receiptApiController.getReceiptById(id);
        Assert.assertEquals("class Receipt {\n" +
                "    receiptId: " + id + "\n" +
                "    creditCardNum: Ends with 2333\n" +
                "    creditCardMonthExp: 02\n" +
                "    creditCardYearExp: 20\n" +
                "    orderId: " + orderId + "\n" +
                "    totalPrice: 0.0\n" +
                "}", receipt.toString());

    }


    /**
     * Testing the receipt ApiController using the get method by order id and receipt id
     */
    @Test
    public void receiptControllerGetOrderIdAndReceiptIdTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);
        receiptApiController.addReceiptPost(receipt);
        String receiptId = receipt.getId();

        receiptApiController.getOrderbyIDandReceiptById(receiptId, orderId);

        Assert.assertEquals(1, orderRepository.count());
    }

    /**
     * Testing the receipt ApiController using the post method by order id and receipt id
     */
    @Test
    public void receiptControllerPostOrderIdAndReceiptIdTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "02", "20", orderId);
        receiptApiController.addReceiptPost(receipt);
        String receiptId = receipt.getId();

        receiptApiController.postOrderbyIDandReceiptById(receiptId, orderId);

        Assert.assertEquals(1, receiptRepository.count());
    }

    /**
     * Testing the receipt id with creditCardMonth method
     */

    @Test
    public void receiptIdTest() {
        Receipt receipt = new Receipt();
        Receipt receiptWithCreditCardMonth = receipt.creditCardMonthExp("12");
        assertEquals(receipt, receiptWithCreditCardMonth);
    }


    /**
     * Testing the receipt id with creditCardYear method
     */
    @Test
    public void receiptCreditCardYearTest() {
        Receipt receipt = new Receipt();
        Receipt receiptWithCreditCardYear = receipt.creditCardYearExp("22");
        assertEquals(receipt, receiptWithCreditCardYear);
    }

    /**
     * Testing the receipt id with creditCardMonth method
     */

    @Test
    public void receiptCreditCardMonthTest() {
        Receipt receipt = new Receipt();
        Receipt receiptWithCreditCard = receipt.creditCardMonthExp("12");
        assertEquals(receipt, receiptWithCreditCard);
    }

    /**
     * Testing the receipt id with creditCardNumber method
     */
    @Test
    public void receiptCreditCardNumberTest() {
        Receipt receipt = new Receipt();
        Receipt receiptWithCreditCardNumber = receipt.creditCardNum("9127365859338948");
        assertEquals(receipt, receiptWithCreditCardNumber);
    }

    /**
     * Testing the receipt with order id
     */

    @Test
    public void receiptOrderIdTest() {
        Receipt receipt = new Receipt();
        Receipt receiptWithOrderId = receipt.orderId("1284");
        assertEquals(receipt, receiptWithOrderId);
    }

    /**
     * Testing the receipt with a bad input for credit card expiration year
     */

    @Test
    public void receiptBadCreditCardYear() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "02", "2020", orderId);

        assertEquals("400", receiptApiController.addReceiptPost(receipt).getStatusCode().toString());
    }

    /**
     * Testing the receipt with a bad input for credit card expiration month
     */
    @Test
    public void receiptBadCreditCardMonthTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "123", "20", orderId);

        assertEquals("400", receiptApiController.addReceiptPost(receipt).getStatusCode().toString());
    }


    /**
     * Testing the receipt with a bad input for credit card expiration with current date
     * Will get a 400 response
     */
    @Test
    public void receiptCreditCardBeforeCurrentDateTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("0000111122223333", "10", "19", orderId);

        assertEquals("400", receiptApiController.addReceiptPost(receipt).getStatusCode().toString());
    }


    /**
     * Testing the receipt with a bad input for credit card number
     * Will get a 400 response
     */
    @Test
    public void receiptBadCreditCardNumberTest() {
        Order order = new Order();
        orderRepository.save(order);
        String orderId = order.getId();

        Receipt receipt = new Receipt("000011112233", "10", "20", orderId);

        assertEquals("400", receiptApiController.addReceiptPost(receipt).getStatusCode().toString());
    }

}
