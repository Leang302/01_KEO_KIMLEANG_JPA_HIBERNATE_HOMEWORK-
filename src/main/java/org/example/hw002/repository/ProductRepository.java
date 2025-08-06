package org.example.hw002.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.hw002.dto.request.ProductRequest;
import org.example.hw002.model.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ProductRepository {
    @PersistenceContext
    private final EntityManager em;


    public Product createProduct(Product product) {
        em.persist(product);
        return  product;
    }

    public Product getProductById(Long productId) {
        return em.find(Product.class, productId);
    }

    public Product updateProductById(Long productId, ProductRequest request) {
        Product product = em.find(Product.class, productId);
        em.detach(product);
        product.setName(request.getName().trim());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        em.merge(product);
        return product;
    }

    public void deleteProductById(Long productId) {
        Product product = em.find(Product.class, productId);
        em.remove(product);
    }

    public List<Product> getAllProducts(Integer page, Integer size) {
        return em.createQuery("SELECT p FROM Product  p",Product.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countAllProducts() {
        return em.createQuery("SELECT count(p) from Product  p",Long.class)
                .getSingleResult();
    }

    public List<Product> getProductByProductName(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.name ILIKE :name",Product.class)
                .setParameter("name","%"+name+"%")
                .getResultList();
    }

    public List<Product> getLowStockProductByProductQuantity(Long quantity) {
        return em.createQuery("SELECT p FROM Product p WHERE p.quantity<:qty",Product.class)
                .setParameter("qty",quantity).getResultList();
    }
}
