package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderManagementService.controller.OrderItemServiceController;
import com.orderManagementService.dao.OrderItemDetailsDao;
import com.orderManagementService.model.OrderItemDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemsDetailsControllerTest {

    @InjectMocks
    OrderItemServiceController orderItemServiceController;

    @Mock
    OrderItemDetailsDao orderItemDetailsDao;

    ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderItemDetailsDao).build();
    }

    // test case - create orderItem details
    @Test
    public void testCreateOrderItemDetails() throws Exception {
        when(orderItemDetailsDao.save(any(OrderItemDetails.class))).thenReturn(getOrderItemDetails());
        String orderItemDetails = mapper.writeValueAsString(getOrderItemDetails());
        MockHttpServletResponse response = mockMvc.perform(post("/OrderItemDetails/createOrderItemDetails").contentType("application/json").content(orderItemDetails))
                .andExpect(status().isOk()).andReturn().getResponse();
        OrderItemDetails resultOrderItemDetails = getResultOrderItemDetails(response);
        assertEquals(getOrderItemDetails().getId(), resultOrderItemDetails.getId());
        assertEquals(getOrderItemDetails().getProductCode(), resultOrderItemDetails.getProductCode());
        assertEquals(getOrderItemDetails().getProductName(), resultOrderItemDetails.getProductName());
        assertEquals(getOrderItemDetails().getQuantity(), resultOrderItemDetails.getQuantity());
    }

    private OrderItemDetails getOrderItemDetails() {
        OrderItemDetails orderItemDetails = new OrderItemDetails();
        orderItemDetails.setId(1);
        orderItemDetails.setProductCode("1234345");
        orderItemDetails.setProductName("Chile");
        orderItemDetails.setQuantity("2");
        return orderItemDetails;
    }

    private OrderItemDetails getResultOrderItemDetails(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), OrderItemDetails.class);
    }

    @Test
    public void getOrderDetailsTest() throws Exception {
        when(orderItemDetailsDao.findone(1)).thenReturn(getOrderItemDetails());
        MockHttpServletResponse response = mockMvc.perform(get("/OrderItemDetails/getOrderItemInfoByID/{id}", 1))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        OrderItemDetails resultOrderItemDetails = getResultOrderItemDetails(response);
        assertEquals(getOrderItemDetails().getQuantity(), resultOrderItemDetails.getQuantity());
        assertEquals(getOrderItemDetails().getProductName(), resultOrderItemDetails.getProductName());
        assertEquals(getOrderItemDetails().getProductCode(), resultOrderItemDetails.getProductCode());
        assertEquals(getOrderItemDetails().getId(), resultOrderItemDetails.getId());
    }

    @Test
    public void testFindAll() throws Exception {

        List<OrderItemDetails> orderDetailsArrayList = new ArrayList<OrderItemDetails>();
        orderDetailsArrayList.add(getOrderItemDetails());

        when(orderItemDetailsDao.findAll()).thenReturn(orderDetailsArrayList);
        MockHttpServletResponse response = mockMvc.perform(get("/OrderDetails/allOrderDetails"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        List<OrderItemDetails> allOrderDetails = getAllItemDetails(response);
        assertEquals(1, allOrderDetails.size());
    }

    private List<OrderItemDetails> getAllItemDetails(MockHttpServletResponse response) throws IOException {
        return mapper.readValue(response.getContentAsString(), new TypeReference<List<OrderItemDetails>>() {
        });
    }
}

