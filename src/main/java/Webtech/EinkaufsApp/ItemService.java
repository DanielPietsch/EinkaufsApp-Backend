package Webtech.EinkaufsApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public Item save(Item item) {
        return repo.save(item);
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        repo.findAll().forEach(items::add);
        return items;
    }
}
