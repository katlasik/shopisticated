package pl.atk.shopisticated.repositories;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;
import pl.atk.shopisticated.model.Order;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {

    @VisibleForTesting
    final List<Order> orders = Lists.newArrayList();

    public Order findOne(UUID id) {
        return orders.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public long count() {
        return orders.size();
    }

    public List<Order> findAll() {
        return orders;
    }

    public Order save(Order entity) {
        orders.add(entity);
        return entity;
    }

}
