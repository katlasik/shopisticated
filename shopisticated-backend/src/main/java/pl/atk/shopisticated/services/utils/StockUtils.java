package pl.atk.shopisticated.services.utils;

import lombok.NonNull;
import pl.atk.shopisticated.exceptions.UnsufficientItemsException;
import pl.atk.shopisticated.exceptions.UnknownItemException;
import pl.atk.shopisticated.model.Item;

public class StockUtils {

    public static Item checkNotNull(Item item) {
        if (item == null) {
            throw new UnknownItemException();
        }
        return item;
    }

    public static Item checkAvailable(@NonNull Item item, int quantity) {
        if (item.getAvailable() - quantity < 0) {
            throw new UnsufficientItemsException();
        }
        return item;
    }

}
