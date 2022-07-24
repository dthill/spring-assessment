package pgfsd.sportyshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pgfsd.sportyshoes.entities.Purchase;

@Getter
@Setter
@AllArgsConstructor
public class CartDto {
    private Purchase purchase;
    private Double total;
}
