package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.model.Items;
import com.example.demo.model.Sales;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SalesRepositoryTest {

    @Autowired
    SalesRepository salesRepository;
    Sales sales=new Sales(1,true,3,600.99);


    @Test
    void fetchAllSales() {
    assertThat(salesRepository.fetchAllSales()).isNotEmpty();
    }

    @Test
    void findSalesById() {
        Items items= new Items(1,1,"M",200,"TEst",400,"shirt",2,4,"");
        salesRepository.addSales(sales,items);
        assertThat(salesRepository.findSalesById(4));
    }

    @Test
    void updateSales() {
        sales.setFinal_price(2000);
        assertThat(salesRepository.updateSales(sales,4));
    }

    @Test
    void findSalesByCustomerId() {
        assertThat(salesRepository.findSalesByCustomerId(3)).isNotEmpty();
    }

}