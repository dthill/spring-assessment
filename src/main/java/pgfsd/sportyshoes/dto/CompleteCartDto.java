package pgfsd.sportyshoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pgfsd.sportyshoes.entities.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CompleteCartDto {
    @NotNull
    private Integer purchaseId;
    @Min(1000)
    private Long creditCardNumber;

    public CompleteCartDto() {
    }
}
