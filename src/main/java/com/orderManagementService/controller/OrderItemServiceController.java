package com.orderManagementService.controller;

import com.orderManagementService.dao.OrderItemDetailsDao;
import com.orderManagementService.model.OrderItemDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/OrderItemDetails")
public class OrderItemServiceController {

    @Autowired
    OrderItemDetailsDao orderItemDetailsDao;

    // save order details to Database
    @PostMapping("/createOrderItemDetails")
    public OrderItemDetails createOrderItemDetails(@Valid @RequestBody OrderItemDetails orderItemDetails) {
        return orderItemDetailsDao.save(orderItemDetails);
    }

    //to retrieve All order details
    @GetMapping("/allOrderItemDetails")
    public List<OrderItemDetails> getAllInformation() {
        return orderItemDetailsDao.findAll();
    }

    // Retrieve personal information by ID
    @GetMapping("/getOrderItemInfoByID/{id}")
    public ResponseEntity<OrderItemDetails> getOrderItemDetailsByID(@PathVariable(value = "id") long id) {
        OrderItemDetails orderItemDetails = orderItemDetailsDao.findone(id);

        if (orderItemDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderItemDetails);

    }

    //update personal information by ID
    @PutMapping("/updateOrderItemInfo/{id}")
    public ResponseEntity<OrderItemDetails> updateOrderItemDetails(@PathVariable(value = "id") long id, @Valid @RequestBody OrderItemDetails infoDetails) {
        OrderItemDetails orderItemDetails = orderItemDetailsDao.findone(id);
        if (orderItemDetails == null) {
            return ResponseEntity.notFound().build();
        } else {


            OrderItemDetails updateOrderDetails = orderItemDetailsDao.save(orderItemDetails);
            return ResponseEntity.ok().body(updateOrderDetails);
        }

    }


    //Delete personal information

    @DeleteMapping("/deleteOrderItemInfo/{id}")
    public ResponseEntity<OrderItemDetails> deletePersonalInfo(@PathVariable(value = "id") long id) {
        OrderItemDetails orderItemDetails = orderItemDetailsDao.findone(id);
        if (orderItemDetails == null) {
            return ResponseEntity.notFound().build();
        }
        orderItemDetailsDao.delete(orderItemDetails);
        return ResponseEntity.ok().build();
    }

}



