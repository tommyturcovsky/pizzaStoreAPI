package io.swagger.model;

import org.threeten.bp.LocalDateTime;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.*;

/**
 * Receipt
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-11T04:06:56.794Z")
@Document(collection = "receipt")
public class Receipt {
    @Id
    @JsonProperty("id")
    private String id = null;

    @JsonProperty("creditCardNum")
    private String creditCardNum = null;

    @JsonProperty("creditCardMonthExp")
    private String creditCardMonthExp = null;

    @JsonProperty("creditCardYearExp")
    private String creditCardYearExp = null;

    @JsonProperty("orderId")
    private String orderId = null;

    @JsonProperty
    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime localDateTime;

    @JsonProperty("totalPrice")
    private Double totalPrice = 0.00;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Default constructor for receipt
     */
    public Receipt() {
    }


    /**
     * Constructor to pass json objects through to be deserialized
     *
     * @param creditCardNum
     * @param creditCardMonthExp
     * @param creditCardYearExp
     * @param orderId
     */

    @JsonCreator
    public Receipt(@JsonProperty("creditCardNum") String creditCardNum, @JsonProperty("creditCardMonthExp") String creditCardMonthExp,
                   @JsonProperty("creditCardYearExp") String creditCardYearExp, @JsonProperty("orderId") String orderId) {
        this.creditCardNum = creditCardNum;
        this.creditCardMonthExp = creditCardMonthExp;
        this.creditCardYearExp = creditCardYearExp;
        this.orderId = orderId;
    }


    public Receipt creditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
        return this;
    }

    /**
     * Get order id
     *
     * @return id
     */

    public String getId() {
        return this.id;
    }

    /**
     * Set the id for receipt
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get creditCardNum
     *
     * @return creditCardNum
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull


    /**
     * Get Credit Card Number
     * @return credit card number
     */

    public String getCreditCardNum() {
        return creditCardNum;
    }

    /**
     * Set Credit Card Number
     *
     * @param creditCardNum
     * @return creditCardNum
     */

    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    /**
     * Set credit Card month expiration
     *
     * @param creditCardMonthExp
     * @return credit card month expiration
     */
    public Receipt creditCardMonthExp(String creditCardMonthExp) {
        this.creditCardMonthExp = creditCardMonthExp;
        return this;
    }

    /**
     * Set anonymous Credit Card Number
     *
     * @return anonymous cc number
     */
    public void setAnonymousCCNumber() {
        String anonymousCCNum = this.creditCardNum;
        anonymousCCNum = anonymousCCNum.substring(anonymousCCNum.length() - 5, anonymousCCNum.length() - 1);
        this.creditCardNum = "Ends with " + anonymousCCNum;
    }

    /**
     * Get creditCardMonthExp
     *
     * @return creditCardMonthExp
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getCreditCardMonthExp() {
        return creditCardMonthExp;
    }

    public void setCreditCardMonthExp(String creditCardMonthExp) {
        this.creditCardMonthExp = creditCardMonthExp;
    }

    public Receipt creditCardYearExp(String creditCardYearExp) {
        this.creditCardYearExp = creditCardYearExp;
        return this;
    }

    /**
     * Get creditCardYearExp
     *
     * @return creditCardYearExp
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getCreditCardYearExp() {
        return creditCardYearExp;
    }

    public void setCreditCardYearExp(String creditCardYearExp) {
        this.creditCardYearExp = creditCardYearExp;
    }

    public Receipt orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Get orderId
     *
     * @return orderId
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull


    /**
     * Get order Id
     * @return order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Set order Id
     *
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Set localDateTime
     *
     * @param localDateTime
     * @return localDateTime
     */
    public Receipt localDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    /**
     * Get localDateTime
     *
     * @return localDateTime
     **/
    @ApiModelProperty(required = true, value = "")


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Set localDateTime
     *
     * @return localDateTime
     */
    public LocalDateTime setLocalDateTime() {
        localDateTime = LocalDateTime.now();
        return localDateTime;
    }

    /**
     * Total Price of the order
     *
     * @return totalPrice
     **/

    public Receipt totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    /**
     * Get totalPrice
     *
     * @return totalPrice
     **/
    @ApiModelProperty(required = true, value = "")

    /**
     * Get total Price of the order
     * @return totalPrice
     */

    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Set total Price of the order
     *
     * @param totalPrice
     * @return totalPrice
     */


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receipt receipt = (Receipt) o;
        return Objects.equals(this.id, receipt.id) &&
                Objects.equals(this.creditCardNum, receipt.creditCardNum) &&
                Objects.equals(this.creditCardMonthExp, receipt.creditCardMonthExp) &&
                Objects.equals(this.creditCardYearExp, receipt.creditCardYearExp) &&
                Objects.equals(this.orderId, receipt.orderId) &&
                Objects.equals(this.totalPrice, receipt.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creditCardNum, creditCardMonthExp, creditCardYearExp, orderId, totalPrice);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Receipt {\n");

        sb.append("    receiptId: ").append(toIndentedString(id)).append("\n");
        sb.append("    creditCardNum: ").append(toIndentedString(creditCardNum)).append("\n");
        sb.append("    creditCardMonthExp: ").append(toIndentedString(creditCardMonthExp)).append("\n");
        sb.append("    creditCardYearExp: ").append(toIndentedString(creditCardYearExp)).append("\n");
        sb.append("    orderId: ").append(toIndentedString(orderId)).append("\n");
        sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

