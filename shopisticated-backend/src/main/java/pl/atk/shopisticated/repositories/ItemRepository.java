package pl.atk.shopisticated.repositories;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.val;
import org.springframework.stereotype.Repository;
import pl.atk.shopisticated.exceptions.UnknownItemException;
import pl.atk.shopisticated.model.Item;

import java.util.List;
import java.util.UUID;

@Repository
public class ItemRepository {

    @VisibleForTesting
    final List<Item> items = Lists.newArrayList(ImmutableList
            .<Item>builder()
            .add(new Item(
                    UUID.fromString("77433ce3-86be-4a68-af60-843bb4de076e"),
                    "Item A",
                    "Lorem ipsum",
                    20,
                    "static/images/a.jpg"
                    ))
            .add(new Item(
                    UUID.fromString("fcfe7c54-2499-4226-a541-91cddb94f526"),
                    "Item B",
                    "Lorem ipsum",
                    10,
                    "static/images/b.jpg"))
            .build());

    public Item findOne(UUID id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public long count() {
        return items.size();
    }

    public List<Item> findAll() {
        return items;
    }

    public Item save(Item entity) {

        val item = items.stream()
            .filter(i -> i.getId().equals(entity.getId()))
            .findFirst()
            .orElseThrow(UnknownItemException::new);

        item.setAvailable(entity.getAvailable());
        item.setDescription(entity.getDescription());
        item.setName(entity.getName());
        item.setImage(entity.getImage());

        return item;
    }

}
