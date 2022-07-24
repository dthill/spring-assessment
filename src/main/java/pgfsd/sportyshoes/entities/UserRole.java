package pgfsd.sportyshoes.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @Column(nullable = false)
    private Role role;

    public UserRole() {
    }

    public enum Role {
        CUSTOMER,
        ADMIN
    }

}