package pl.atk.shopisticated.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private UUID id;
    private UUID itemId;
    private String name;
    private Integer quantity;
    private LocalDateTime date;
}
