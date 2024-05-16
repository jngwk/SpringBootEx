package com.conan.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.conan.demo.domain.Product;
import com.conan.demo.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert() {
        for (int i = 1; i <= 10; i++) {
            Product product = Product.builder()
                    .pname("상품 ..." + i).price(1000 * i).pdesc("상품설명 " + i).build();
            product.addImageString(UUID.randomUUID().toString() + "_" + "image1.jpg");
            product.addImageString(UUID.randomUUID().toString() + "_" + "image2.jpg");
            productRepository.save(product);
            log.info("-----------------------------------------------");
        }
    }

    @Transactional
    @Test
    public void testRead() {
        Integer pno = 1;
        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();
        log.info(product);
        log.info(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        Integer pno = 3;
        productRepository.updateToDelete(pno, true);
    }

    @Test
    public void testModify() {
        Integer pno = 10;
        // 수정하려는 상품 객체 가져오기
        Product product = productRepository.selectOne(pno).get();
        product.setPname("10번 상품");
        product.setPdesc("10번 상품 설명");
        product.setPrice(5000);
        // 첨부파일 수정 → 기존의 첨부파일 삭제 + 이미지 정보 새로 추가
        product.clearList(); // 기존의 첨부파일 삭제
        product.addImageString(UUID.randomUUID().toString() + "_" + "newimage1.jpg");
        product.addImageString(UUID.randomUUID().toString() + "_" + "newimage2.jpg");
        product.addImageString(UUID.randomUUID().toString() + "_" + "newimage3.jpg");
        productRepository.save(product);
    }

    @Test
    public void testList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
    }
}
