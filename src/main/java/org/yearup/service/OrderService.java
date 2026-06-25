package org.yearup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yearup.models.*;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;

import java.time.LocalDateTime;

@Service
public class OrderService
{
    private OrderRepository orderRepository;
    private OrderLineItemRepository orderLineItemRepository;
    private ShoppingCartService shoppingCartService;
    private ProfileService profileService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderLineItemRepository orderLineItemRepository,
                        ShoppingCartService shoppingCartService,
                        ProfileService profileService)
    {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.profileService = profileService;
    }

    public Order checkout(int userId)
    {
        Profile profile = profileService.getByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());
        order.setAddress(profile.getAddress());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setZip(profile.getZip());
        order.setShippingAmount(0);

        Order savedOrder = orderRepository.save(order);

        ShoppingCart cart = shoppingCartService.getByUserId(userId);

        for (ShoppingCartItem cartItem : cart.getItems().values())
        {
            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setOrderId(savedOrder.getOrderId());
            lineItem.setProductId(cartItem.getProductId());
            lineItem.setSalesPrice(cartItem.getProduct().getPrice());
            lineItem.setQuantity(cartItem.getQuantity());
            lineItem.setDiscount(cartItem.getDiscountPercent());

            orderLineItemRepository.save(lineItem);
        }

        shoppingCartService.clearCart(userId);

        return savedOrder;
    }
}