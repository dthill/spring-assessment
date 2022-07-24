package pgfsd.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgfsd.sportyshoes.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product save(Product product);

    Product findProductById(Integer id);

    List<Product> findAll();
}
