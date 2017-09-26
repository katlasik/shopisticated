package pl.atk.shopisticated.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.atk.shopisticated.model.Item;
import pl.atk.shopisticated.repositories.ItemRepository;

import java.util.List;

@AllArgsConstructor
@Controller
@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> items() {
        return itemRepository.findAll();
    }


}
