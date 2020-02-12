package io.swagger.api;

import io.swagger.model.Order;
import io.swagger.model.Receipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.repository.OrderRepository;
import io.swagger.repository.ReceiptRepository;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-10T09:06:43.535Z")

@Controller
public class ReceiptApiController implements ReceiptApi {

    private static final Logger log = LoggerFactory.getLogger(ReceiptApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private OrderRepository orderRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public ReceiptApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Receipt> addReceiptPost(@ApiParam(value = "", required = true) @Valid @RequestBody Receipt body) {
        String accept = request.getHeader("Accept");
        Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
        Calendar cal = Calendar.getInstance();
        cal.setTime(today); // don't forget this if date is arbitrary e.g. 01-01-2014
        int month = cal.get(Calendar.MONTH); // 5
        int year = cal.get(Calendar.YEAR); // 2016
        int inputCreditCardYear;
        int inputCreditCardMonth;
        long inputCreditCardNum;

        // Parsing Credit Card Year & Month Expiration
        try {
            if (body.getCreditCardYearExp().length() > 2) {
                throw new IllegalArgumentException("Longer than year format");
            }
            inputCreditCardYear = Integer.parseInt(body.getCreditCardYearExp()) + 2000;
        } catch (Exception e) {
            log.error("Can not cast YearExp to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            if (body.getCreditCardMonthExp().length() > 2) {
                throw new IllegalArgumentException("Longer than month format");
            }
            inputCreditCardMonth = Integer.parseInt(body.getCreditCardMonthExp());
        } catch (Exception e) {
            log.error("Can not cast MonthExp to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Checking to see if Credit Card number is valid
        try {
            if (body.getCreditCardNum().length() < 16) {
                throw new NumberFormatException();
            }
            inputCreditCardNum = Long.parseLong(body.getCreditCardNum());
        } catch (NumberFormatException e) {
            log.error("Can not cast CreditCardNum to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Checking to see if Credit Card Expiration is valid
        try {
            if (inputCreditCardYear < year) {
                throw new IllegalArgumentException("Credit Card Year is before current Year");
            } else if (inputCreditCardYear == year && inputCreditCardMonth < month) {
                throw new IllegalArgumentException("Credit Card expiration is before current year");
            }
        } catch (IllegalArgumentException e) {
            log.error("Credit Card expiration is before current date", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        body.setLocalDateTime();
        body.setAnonymousCCNumber();
        String orderId = body.getOrderId();
        Order order = orderRepository.findById(orderId);
        Double totalPrice = order.getTotalPrice();
        body.setTotalPrice(totalPrice);

        receiptRepository.deleteAll();
        receiptRepository.save(body);
        return new ResponseEntity<Receipt>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<Receipt> addReceiptPut(@ApiParam(value = "", required = true) @Valid @RequestBody Receipt body) {
        String accept = request.getHeader("Accept");
        Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016
        Calendar cal = Calendar.getInstance();
        cal.setTime(today); // don't forget this if date is arbitrary e.g. 01-01-2014
        int month = cal.get(Calendar.MONTH); // 5
        int year = cal.get(Calendar.YEAR); // 2016
        int inputCreditCardYear;
        int inputCreditCardMonth;
        long inputCreditCardNum;

        // Parsing Credit Card Year & Month Expiration
        try {
            if (body.getCreditCardYearExp().length() > 2) {
                throw new IllegalArgumentException("Longer than year format");
            }
            inputCreditCardYear = Integer.parseInt(body.getCreditCardYearExp()) + 2000;
        } catch (Exception e) {
            log.error("Can not cast YearExp to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            if (body.getCreditCardMonthExp().length() > 2) {
                throw new IllegalArgumentException("Longer than month format");
            }
            inputCreditCardMonth = Integer.parseInt(body.getCreditCardMonthExp());
        } catch (Exception e) {
            log.error("Can not cast MonthExp to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Checking to see if Credit Card number is valid
        try {
            if (body.getCreditCardNum().length() < 16 || body.getCreditCardNum().length() > 16) {
                throw new NumberFormatException();
            }
            inputCreditCardNum = Long.parseLong(body.getCreditCardNum());
        } catch (NumberFormatException e) {
            log.error("Can not cast CreditCardNum to Integer, value was something other than numbers", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Checking to see if Credit Card Expiration is valid
        try {
            if (inputCreditCardYear + 2000 < year) {
                throw new IllegalArgumentException("Credit Card Year is before current Year");
            } else if (inputCreditCardYear == year && inputCreditCardMonth < month) {
                throw new IllegalArgumentException("Credit Card expiration is before current year");
            }
        } catch (Exception e) {
            log.error("Credit Card expiration is before current date", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        body.setLocalDateTime();
        body.setAnonymousCCNumber();
        String orderId = body.getOrderId();
        Order order = orderRepository.findById(orderId);
        Double totalPrice = order.getTotalPrice();
        body.setTotalPrice(totalPrice);

        receiptRepository.save(body);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deleteAllReceipts() {
        String accept = request.getHeader("Accept");
        if (receiptRepository.count() > 0) {
            receiptRepository.deleteAll();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<Receipt> deleteReceiptById(@ApiParam(value = "reciptId", required = true) @PathVariable("receiptId") String receiptId) {
        String accept = request.getHeader("Accept");
        receiptRepository.deleteById(receiptId);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<List<Receipt>> getAllReceipts() {
        List<Receipt> storeList = receiptRepository.findAll();
        return new ResponseEntity<List<Receipt>>(storeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/receipt/{receiptId}", method = RequestMethod.GET)
    public ResponseEntity<Receipt> getReceiptById(@ApiParam(value = "reciptId", required = true) @PathVariable("receiptId") String receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId);
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

    @RequestMapping(value = "/receipt/{receiptId}/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderbyIDandReceiptById(@ApiParam(value = "reciptId", required = true) @PathVariable("receiptId") String receiptId, @ApiParam(value = "orderId", required = true) @PathVariable("orderId") String orderId) {
        String accept = request.getHeader("Accept");
        Receipt receipt = receiptRepository.findById(receiptId);

        if (receipt == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //Return the order
        String orderIdFromStore = receipt.getOrderId();
        Order order = orderRepository.findById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);


    }

    @RequestMapping(value = "/receipt/{receiptId}/order/{orderId}", method = RequestMethod.POST)
    public ResponseEntity<Receipt> postOrderbyIDandReceiptById(@ApiParam(value = "receiptId", required = true) @PathVariable("receiptId") String receiptId, @ApiParam(value = "orderId", required = true) @PathVariable("orderId") String orderId) {
        String accept = request.getHeader("Accept");
        Receipt receipt = receiptRepository.findById(receiptId);

        receiptRepository.deleteById(receiptId);

        //Make a receipt with new orderID
        Order orderToAddToReceipt = orderRepository.findById(orderId);
        receipt.setOrderId(orderId);

        // Put Receipt object in Receipt Repo
        receiptRepository.save(receipt);


        return new ResponseEntity<>(receipt, HttpStatus.CREATED);
    }

}