package org.example.hw002.service;

import org.example.hw002.dto.request.ProductRequest;
import org.example.hw002.dto.response.PayloadResponse;
import org.example.hw002.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductRequest request);

    Product updateProduct(Long productId,ProductRequest request);

    Product getProductById(Long productId);

    void deleteProduct(Long productId);

    PayloadResponse<List<Product>> getAllProducts(Integer page, Integer size);

    List<Product> getProductByProductName(String name);

    List<Product> getLowStockProductByProductQuantity(Long quantity);
}
