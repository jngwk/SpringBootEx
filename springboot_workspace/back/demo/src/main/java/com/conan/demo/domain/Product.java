package com.conan.demo.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@ToString(exclude = "imageList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean flag; // 상품 삭제시 구매 기록 관리를 위해 삭제된 상품 표시
    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void addImage(ProductImage image) { // 이미지 추가
        image.setOrd(this.imageList.size());
        imageList.add(image);
    }

    public void addImageString(String fileName) { // 파일 추가
        ProductImage productImage = ProductImage.builder().fileName(fileName).build();
        addImage(productImage);
    }

    public void clearList() {
        this.imageList.clear();
    }
}