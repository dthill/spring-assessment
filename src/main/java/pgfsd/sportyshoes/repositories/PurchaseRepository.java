package pgfsd.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pgfsd.sportyshoes.entities.Purchase;
import pgfsd.sportyshoes.entities.User;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByPurchasedOnIsNotNull();

    Purchase findByBuyerAndPurchasedOnOrderByCreatedOnDesc(User user, Date purchasedOn);

    Purchase findByIdAndBuyer(Integer id, User user);

    List<Purchase> findAllByPurchasedOn(Date purchasedOn);

    @Query("SELECT DISTINCT p FROM Purchase p JOIN p.products prod JOIN prod.productCategories c WHERE c.id = ?1")
    List<Purchase> findAllByCategory(Integer categoryId);

    @Query("SELECT DISTINCT purchasedOn FROM Purchase WHERE purchasedOn IS NOT NULL")
    List<Date> findDistinctPurchasedOn();

    Purchase save(Purchase purchase);
}