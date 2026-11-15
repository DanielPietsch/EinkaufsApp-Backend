package Webtech.EinkaufsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

  @Autowired
  private ItemService service;

  @GetMapping("/items")
  public List<Item> getItems() {
    return service.getAll();
  }

  @PostMapping("/items")
  public Item createItem(@RequestBody Item item) {
    return service.save(item);
  }
}