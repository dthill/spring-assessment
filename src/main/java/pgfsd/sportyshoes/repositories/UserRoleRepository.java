package pgfsd.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.entities.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(UserRole.Role role);
    UserRole save(UserRole user);
    long count();
}