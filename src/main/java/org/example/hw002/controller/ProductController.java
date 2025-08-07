package org.example.hw002.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.hw002.dto.request.ProductRequest;
import org.example.hw002.dto.response.ApiResponse;
import org.example.hw002.dto.response.PayloadResponse;
import org.example.hw002.model.entity.Product;
import org.example.hw002.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Operation(summary = "Get product by product id")
    @GetMapping("{product-id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable("product-id") Long productId){
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .code(HttpStatus.OK)
                        .message("Product with id "+productId+" fetched successfully.")
                        .payload(productService.getProductById(productId))
                        .build()
        );
    }
    //get all
    @Operation(summary = "Get all products (paginated) ")
    @GetMapping
    public ResponseEntity<ApiResponse<PayloadResponse<List<Product>>>> getAllProduct(@Positive @RequestParam(defaultValue = "1") Integer page,@Positive @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(
                ApiResponse.<PayloadResponse<List<Product>>>builder()
                        .code(HttpStatus.OK)
                        .message("Products fetched successfully")
                        .payload(productService.getAllProducts(page,size))
                        .build()
        );
    }
    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid  @RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.<Product>builder()
                    .code(HttpStatus.CREATED)
                    .message("Product created successfully.")
                    .payload(productService.createProduct(request))
                    .build()
        );
    }

    @Operation(summary = "Update product by product id")
    @PutMapping("{product-id}")
    public ResponseEntity<ApiResponse<Product>> updateProductById(@PathVariable("product-id") Long productId,@Valid @RequestBody ProductRequest request){
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .code(HttpStatus.OK)
                        .message("Product with id "+productId+" updated successfully.")
                        .payload(productService.updateProduct(productId,request))
                        .build()
        );
    }
    //delete product
    @Operation(summary = "Delete product by product id")
    @DeleteMapping("{product-id}")
    public ResponseEntity<ApiResponse<Product>> deleteProductById(@PathVariable("product-id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(
                ApiResponse.<Product>builder()
                        .code(HttpStatus.OK)
                        .message("Product with id "+productId+" deleted successfully.")
                        .build()
        );
    }

    @Operation(summary = "Find product by product name")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Product>>> getProductByProductName(@RequestParam String name){
        return ResponseEntity.ok(
                ApiResponse.<List<Product>>builder()
                        .code(HttpStatus.OK)
                        .payload(productService.getProductByProductName(name.trim()))
                        .message("Product with name "+name+" fetched successfully.")
                        .build()
        );
    }
    //find product with qty filter
    @Operation(summary = "Get low stocks products")
    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<Product>>> getLowStockProductByProductQuantity(@Positive @RequestParam Long quantity){
        return ResponseEntity.ok(
                ApiResponse.<List<Product>>builder()
                        .code(HttpStatus.OK)
                        .payload(productService.getLowStockProductByProductQuantity(quantity))
                        .message("Products with quantity less than "+ quantity +" fetched successfully")
                        .build()
        );
    }
}
