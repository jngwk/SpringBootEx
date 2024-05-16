package com.conan.demo.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.conan.demo.dto.PageRequestDTO;
import com.conan.demo.dto.PageResponseDTO;
import com.conan.demo.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);
        result.getDtoList().forEach(dto -> log.info(dto));
    }

    @Test
    public void testRegister() {
        ProductDTO productDTO = ProductDTO.builder()
                .pname("신상품")
                .pdesc("핫한 신상")
                .price(10000)
                .build();
        productDTO.setUploadFileNames(
                List.of(
                        UUID.randomUUID() + "_" + "test1.jpg",
                        UUID.randomUUID() + "_" + "test2.jpg"));
        productService.register(productDTO);
    }

    @Test
    public void testGet() {
        Integer pno = 9;
        ProductDTO productDTO = productService.get(pno);
        log.info(productDTO);
        log.info(productDTO.getUploadFileNames());
    }
}
