package pl.atk.shopisticated.services;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import pl.atk.shopisticated.model.Order;
import pl.atk.shopisticated.repositories.ItemRepository;
import pl.atk.shopisticated.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static pl.atk.shopisticated.services.utils.StockUtils.checkAvailable;
import static pl.atk.shopisticated.services.utils.StockUtils.checkNotNull;

@Service
@AllArgsConstructor
public class OrderService {

    private ItemRepository itemRepository;
    private OrderRepository orderRepository;

    public Order purchase(Order order) {
        val item = checkAvailable(
                    checkNotNull(itemRepository.findOne(order.getItemId())),
                    order.getQuantity()
                );

        order.setId(UUID.randomUUID());
        order.setDate(LocalDateTime.now());
        order.setName(item.getName());

        orderRepository.save(order);
        item.setAvailable(item.getAvailable() - order.getQuantity());
        itemRepository.save(item);

        return order;
    }

}
