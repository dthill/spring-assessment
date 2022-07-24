package pgfsd.sportyshoes.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {
    @Size(min = 3, max = 50)
    private String username;
    @Size(min = 3, max = 50)
    private String password;
}
