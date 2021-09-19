package ptit.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ptit.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface extends UserDetailsService {
    public UserEntity addUser(UserEntity user);
    public UserEntity findOne(Long id);
    public UserEntity findByEmail(String email);
    public List<UserEntity> findAllByUsername(String username);
}
