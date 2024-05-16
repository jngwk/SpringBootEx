package com.conan.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean flag;
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>(); // 서버에 저장되는 실제 파일 데이터
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>(); // 데이터베이스에 저장될 파일 이름
}