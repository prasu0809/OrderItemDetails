package com.orderManagementService.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/*
@Getter
@Setter*/
@Entity
@Table(name="OrderDetails")
@EntityListeners(AuditingEntityListener.class)
public class OrderItemDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;

    @NotBlank
    String productCode;

    @NotBlank
    String productName;

    @NotBlank
    String quantity;


    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
