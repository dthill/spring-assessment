package pgfsd.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgfsd.sportyshoes.entities.ProductCategory;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    ProductCategory save(ProductCategory productCategory);

    ProductCategory findProductCategoryById(Integer id);

    List<ProductCategory> findAll();
}
