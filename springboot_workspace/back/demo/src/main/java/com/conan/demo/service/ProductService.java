package com.conan.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.conan.demo.domain.Product;
import com.conan.demo.domain.ProductImage;
import com.conan.demo.dto.PageRequestDTO;
import com.conan.demo.dto.PageResponseDTO;
import com.conan.demo.dto.ProductDTO;
import com.conan.demo.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("getList.................");
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(), Sort.by("pno").descending());
        Page<Object[]> result = productRepository.selectList(pageable);
        List<ProductDTO> dtoList = result.get().map(arr -> {
            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];
            ProductDTO productDTO = ProductDTO.builder()
                    .pno(product.getPno()).pname(product.getPname()).pdesc(product.getPdesc())
                    .price(product.getPrice()).build();
            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));
            return productDTO;
        }).collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        return PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoList).totalCount(totalCount).pageRequestDTO(pageRequestDTO).build();
    }

    public Integer register(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);
        Product result = productRepository.save(product);
        return result.getPno();
    }

    private Product dtoToEntity(ProductDTO productDTO) {
        Product product = Product.builder().pno(productDTO.getPno()).pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc()).price(productDTO.getPrice()).build();
        // 업로드 처리가 끝난 파일들의 이름
        List<String> uploadFileNames = productDTO.getUploadFileNames();
        if (uploadFileNames == null) {
            return product;
        }
        uploadFileNames.stream().forEach(uploadName -> {
            product.addImageString(uploadName);
        });
        return product;
    }

    public ProductDTO get(Integer pno) {
        Optional<Product> result = productRepository.selectOne(pno);
        Product product = result.orElseThrow();
        ProductDTO productDTO = entityToDTO(product);
        return productDTO;
    }

    private ProductDTO entityToDTO(Product product) {
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno()).pname(product.getPname())
                .pdesc(product.getPdesc()).price(product.getPrice()).build();
        List<ProductImage> imageList = product.getImageList();
        if (imageList == null || imageList.size() == 0) {
            return productDTO;
        }
        List<String> fileNameList = imageList.stream().map(productImage -> productImage.getFileName()).toList();
        productDTO.setUploadFileNames(fileNameList);
        return productDTO;
    }

    public void modify(ProductDTO productDTO) {
        // 조회
        Optional<Product> result = productRepository.findById(productDTO.getPno());
        Product product = result.orElseThrow();
        // 수정
        product.setPname(productDTO.getPname());
        product.setPdesc(productDTO.getPdesc());
        product.setPrice(productDTO.getPrice());
        // 파일들 삭제
        product.clearList();
        List<String> uploadFileNames = productDTO.getUploadFileNames();
        if (uploadFileNames != null && uploadFileNames.size() > 0) {
            uploadFileNames.stream().forEach(uploadName -> {
                product.addImageString(uploadName);
            });
        }
        productRepository.save(product);
    }
}
