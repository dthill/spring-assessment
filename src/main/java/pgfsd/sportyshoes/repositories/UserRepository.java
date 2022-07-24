package pgfsd.sportyshoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pgfsd.sportyshoes.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    List<User> findAllByUsernameContainingIgnoreCase(String username);

    User findUserById(Long id);

    User findUserByIdAndPassword(Long id, String password);

    User findUserByUsername(String username);


    User save(User user);
}