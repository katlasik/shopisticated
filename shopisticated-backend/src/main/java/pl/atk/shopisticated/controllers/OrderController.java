package pl.atk.shopisticated.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.atk.shopisticated.model.Order;
import pl.atk.shopisticated.repositories.OrderRepository;
import pl.atk.shopisticated.services.OrderService;

import java.util.List;

@AllArgsConstructor
@Controller
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderService itemService;

    @GetMapping
    public List<Order> orders() {
        return orderRepository.findAll();
    }

    @PostMapping
    public Order post(@RequestBody Order order) {
        return itemService.purchase(order);
    }




}
