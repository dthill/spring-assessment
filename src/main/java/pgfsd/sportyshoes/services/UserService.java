package pgfsd.sportyshoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pgfsd.sportyshoes.dto.UserRegistrationDto;
import pgfsd.sportyshoes.entities.User;
import pgfsd.sportyshoes.entities.UserRole;
import pgfsd.sportyshoes.repositories.UserRepository;
import pgfsd.sportyshoes.repositories.UserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;


    public List<User> getAllUsers(String username) {
        if (username == null || username.equals("")) {
            return userRepository.findAll();
        }
        return userRepository.findAllByUsernameContainingIgnoreCase(username);
    }

    @Transactional
    public User registerUser(UserRegistrationDto userRegistrationDto) {
        User existingUser = userRepository.findUserByUsername(userRegistrationDto.getUsername());
        if (existingUser != null) {
            return null;
        }
        User user = new User();
        Set<UserRole> roles = new HashSet<>();
        UserRole userRole;
        UserRole.Role role;
        if(userRoleRepository.count() == 0){
            role = UserRole.Role.ADMIN;
        } else {
            role = UserRole.Role.CUSTOMER;
        }
        userRole = userRoleRepository.findByRole(role);
        if(userRole == null){
            userRole = userRoleRepository.save(new UserRole(role));
        }
        roles.add(userRole);
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(userRegistrationDto.getPassword());
        user.setUserRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

}
