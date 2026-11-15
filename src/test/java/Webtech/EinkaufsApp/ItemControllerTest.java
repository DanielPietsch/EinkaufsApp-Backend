package Webtech.EinkaufsApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock statt echtem Service
    @MockitoBean
    private ItemService service;

    @Test
    void getItemsShouldReturnItemsAsJson() throws Exception {
        var milch = new Item("Milch", false);
        milch.setId(1L);
        when(service.getAll()).thenReturn(List.of(milch));

        mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("\"name\":\"Milch\"")));
    }

    @Test
    void postItemShouldReturnSavedItem() throws Exception {
        var saved = new Item("Butter", false);
        saved.setId(5L);
        when(service.save(any(Item.class))).thenReturn(saved);

        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Butter\",\"done\":false}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":5")));
    }
}