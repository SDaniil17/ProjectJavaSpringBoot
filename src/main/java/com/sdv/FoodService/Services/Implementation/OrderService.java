package com.sdv.FoodService.Services.Implementation;

import com.sdv.FoodService.Models.FoodOrder;
import com.sdv.FoodService.Models.FoodOrderId;
import com.sdv.FoodService.Models.Order;
import com.sdv.FoodService.Models.Request.FoodOrderRequestModel;
import com.sdv.FoodService.Models.Request.OrderRequestModel;
import com.sdv.FoodService.Models.Response.OrderResponseModel;
import com.sdv.FoodService.Models.User;
import com.sdv.FoodService.Repositories.OrderRepository;
import com.sdv.FoodService.Services.IFoodService;
import com.sdv.FoodService.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    OrderRepository orderRepository;

    @Autowired
    private IFoodService foodService;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order Add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order Add(OrderRequestModel requestOrder, User user) {

        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);

        List<FoodOrder> foodOrders= new LinkedList<>();
        for (FoodOrderRequestModel el:requestOrder.getFoods()) {
            FoodOrder foodOrder = new FoodOrder(order,foodService.FindById(el.getFoodId()),el.getCount());
            foodOrder.setFoodOrderId(new FoodOrderId(el.getFoodId(),order.getId()));
            foodOrders.add(foodOrder);
        }
        order.setFoodOrders(foodOrders);

        return orderRepository.save(order);
    }

    @Override
    public Order GetById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> GetAll() {
        return orderRepository.findAll();
    }


    @Override
    public List<Order> GetByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<OrderResponseModel> GetAllResponseModel() {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetAll()) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }

    @Override
    public Order SetDelivered(Order order) {

        order.setDelivered(true);
        Add(order);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getUser().getEmail());
        message.setSubject("FoodService Order");
        message.setText("Your order is ready, estimated delivery time is 20 minutes.");
        this.emailSender.send(message);

        return order;
    }

    @Override
    public List<OrderResponseModel> GetByUserResponseModel(User user) {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetByUser(user)) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }
}
