package com.orderManagementService.dao;
import com.orderManagementService.model.OrderItemDetails;
import com.orderManagementService.repository.OrderItemDetailsServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemDetailsDao {

    @Autowired
    OrderItemDetailsServiceRepository orderItemDetailsServiceRepository;

    // saves the data to database
    public OrderItemDetails save(OrderItemDetails orderDetails) {
        return orderItemDetailsServiceRepository.save(orderDetails);

    }

    // it retrieves all the Information from the database
    public List<OrderItemDetails> findAll(){
        return orderItemDetailsServiceRepository.findAll();

    }

    // retrieves data based on id
    public OrderItemDetails findone(long id) {
        return orderItemDetailsServiceRepository.findOne(id);

    }

    // deletes the data from the database
    public void delete(OrderItemDetails orderItemDetails) {
        orderItemDetailsServiceRepository.delete(orderItemDetails);
    }



}


