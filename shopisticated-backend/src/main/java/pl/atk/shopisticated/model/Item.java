package pl.atk.shopisticated.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Item {

    private UUID id;
    private String name;
    private String description;
    private int available;
    private String image;

}
