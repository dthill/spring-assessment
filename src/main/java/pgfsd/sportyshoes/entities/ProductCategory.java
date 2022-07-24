package pgfsd.sportyshoes.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @NotNull
    private Integer id;
    @Size(min = 3, max = 50)
    private String name;


    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}