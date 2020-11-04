package com.example.demo.repository;

import com.example.demo.model.Items;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ItemsRepositoryTest {

    @Autowired
    ItemsRepository itemsRepository;

    Items items= new Items(1,1,"size",500.99,"Test item",300,"test_type",3,5);
    @BeforeEach
    void setUp() {
        itemsRepository.addItem(items);
    }

    @Test
    void fetchAllItems() {
        assertThat(itemsRepository.fetchAllItems()).isNotEmpty();
    }

    @Test
    void findItemsByID() {
        assertThat(itemsRepository.findItemsByID(4)).isNotNull();
    }

    @Test
    void updateItems() {
        
    }


}