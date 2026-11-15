package Webtech.EinkaufsApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService service;

    // Mock statt echter Datenbank
    @MockitoBean
    private ItemRepository repository;

    @Test
    void getAllShouldReturnAllItemsFromRepository() {
        var milch = new Item("Milch", false);
        var brot = new Item("Brot", true);
        doReturn(List.of(milch, brot)).when(repository).findAll();

        List<Item> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("Milch", result.get(0).getName());
    }

    @Test
    void saveShouldReturnSavedItemWithId() {
        var saved = new Item("Butter", false);
        saved.setId(42L);
        doReturn(saved).when(repository).save(any(Item.class));

        Item result = service.save(new Item("Butter", false));

        assertEquals(42L, result.getId());
    }
}