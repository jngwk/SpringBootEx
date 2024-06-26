package com.conan.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.conan.demo.dto.PageRequestDTO;
import com.conan.demo.dto.PageResponseDTO;
import com.conan.demo.dto.ProductDTO;
import com.conan.demo.service.ProductService;
import com.conan.demo.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {
    private final CustomFileUtil fileUtil;
    private final ProductService productService;

    @PostMapping("/")
    public Map<String, Integer> register(ProductDTO productDTO) {
        log.info("register : " + productDTO);
        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        productDTO.setUploadFileNames(uploadFileNames);
        log.info(uploadFileNames);
        Integer pno = productService.register(productDTO);
        return Map.of("RESULT", pno);
    }

    @GetMapping("/display/{fileName}")
    public ResponseEntity<Resource> displayFileGet(@PathVariable String fileName) {
        return fileUtil.getFile(fileName);
    }

    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("list................" + pageRequestDTO);
        return productService.getList(pageRequestDTO);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable(name = "pno") Integer pno) {
        return productService.get(pno);
    }

    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable(name = "pno") Integer pno, ProductDTO productDTO) {
        productDTO.setPno(pno);
        ProductDTO oldDTO = productService.get(pno);
        // 기존 파일들(데이터베이스에 저장된 파일 이름)
        List<String> oldFileNames = oldDTO.getUploadFileNames();
        // 새로 업로드해야 하는 파일들
        List<MultipartFile> files = productDTO.getFiles();
        // 새로 업로드된 파일 이름들
        List<String> newUploadFileNames = fileUtil.saveFiles(files);
        // 변화가 없이 유지되는 파일들
        List<String> uploadedFileNames = productDTO.getUploadFileNames();
        // 유지되는 파일들 + 새로 업로드된 파일 이름들이 저장해야하는 파일 목록
        if (newUploadFileNames != null && newUploadFileNames.size() > 0) {
            uploadedFileNames.addAll(newUploadFileNames);
        }
        productService.modify(productDTO);
        if (oldFileNames != null && oldFileNames.size() > 0) {
            List<String> removeFiles = oldFileNames
                    .stream()
                    .filter(fileName -> uploadedFileNames.indexOf(fileName) == -1).collect(Collectors.toList());
            // 파일 삭제
            fileUtil.deleteFiles(removeFiles);
        }
        return Map.of("RESULT", "SUCCESS");
    }
}