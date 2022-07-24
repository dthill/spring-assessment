package pgfsd.sportyshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pgfsd.sportyshoes.entities.ProductCategory;
import pgfsd.sportyshoes.entities.Purchase;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseAdminDto {
    List<Purchase> purchases;
    List<String> formattedDates;
    List<ProductCategory> categories;

    public PurchaseAdminDto() {
    }
}
