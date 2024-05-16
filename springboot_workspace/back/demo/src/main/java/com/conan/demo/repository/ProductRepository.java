package com.conan.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.conan.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Query("update Product p set p.flag = :flag where p.pno = :pno")
    void updateToDelete(@Param("pno") Integer pno, @Param("flag") boolean flag);

    @EntityGraph(attributePaths = "imageList")
    @Query("select p from Product p where p.pno = :pno")
    Optional<Product> selectOne(@Param("pno") Integer pno);

    @Query("select p, pi from Product p left join p.imageList pi where pi.ord = 0 and p.flag = false ")
    Page<Object[]> selectList(Pageable pageable);
}
