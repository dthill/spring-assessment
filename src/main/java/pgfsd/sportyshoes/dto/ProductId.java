package pgfsd.sportyshoes.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductId {
    @NotNull
    private Integer id;

    public ProductId() {
    }
}
