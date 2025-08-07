package org.example.hw002.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw002.dto.request.ProductRequest;
import org.example.hw002.dto.response.PaginationResponse;
import org.example.hw002.dto.response.PayloadResponse;
import org.example.hw002.exceptions.NotFoundException;
import org.example.hw002.model.entity.Product;
import org.example.hw002.repository.ProductRepository;
import org.example.hw002.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductRequest request) {
        return productRepository.createProduct(request.toEntity());
    }

    @Override
    public Product updateProduct(Long productId,ProductRequest request) {
      getProductById(productId);
        return productRepository.updateProductById(productId,request);
    }

    @Override
    public Product getProductById(Long productId) {
        Product productById = productRepository.getProductById(productId);
        if(productById==null){
            throw new NotFoundException("Product with id "+productId+" not found.");
        }
        return productById;
    }

    @Override
    public void deleteProduct(Long productId) {
        getProductById(productId);
        productRepository.deleteProductById(productId);
    }

    @Override
    public PayloadResponse<List<Product>> getAllProducts(Integer page, Integer size) {
        List<Product> allProducts = productRepository.getAllProducts(page, size);

        Long totalElements =  productRepository.countAllProducts();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        PaginationResponse pagination = PaginationResponse.builder()
                .totalElements(totalElements.intValue())
                .currentPage(page)
                .pageSize(size)
                .totalPages(totalPages)
                .build();
        return PayloadResponse.<List<Product>>builder()
                .items(allProducts)
                .pagination(pagination)
                .build();
    }

    @Override
    public List<Product> getProductByProductName(String name) {

        List<Product> productByProductName = productRepository.getProductByProductName(name);
        if(productByProductName.isEmpty()){
            throw new NotFoundException("No products with name "+name+" not found.");
        }
        return productByProductName;
    }

    @Override
    public List<Product> getLowStockProductByProductQuantity(Long quantity) {
        List<Product> lowStockProductByProductQuantity = productRepository.getLowStockProductByProductQuantity(quantity);
        if(lowStockProductByProductQuantity.isEmpty()){
            throw new NotFoundException("No products quantity lower than "+quantity+" not found.");
        }
        return lowStockProductByProductQuantity;
    }
}
