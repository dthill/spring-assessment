package pgfsd.sportyshoes.dto;

import lombok.Getter;
import lombok.Setter;
import pgfsd.sportyshoes.entities.ProductCategory;

import java.util.Date;

@Getter
@Setter
public class PurchaseFilterDto {
    private Date date;
    private Integer categoryId;
}
