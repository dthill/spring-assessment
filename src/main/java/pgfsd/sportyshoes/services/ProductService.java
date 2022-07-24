package pgfsd.sportyshoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pgfsd.sportyshoes.entities.Product;
import pgfsd.sportyshoes.entities.ProductCategory;
import pgfsd.sportyshoes.repositories.ProductCategoryRepository;
import pgfsd.sportyshoes.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Transactional
    public boolean updateProduct(Product product) {
        Product addedProduct = productRepository.save(product);
        return addedProduct != null;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public boolean updateProductCategory(ProductCategory productCategory) {
        ProductCategory category = productCategoryRepository.save(productCategory);
        return category != null;
    }

    public List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }
}
