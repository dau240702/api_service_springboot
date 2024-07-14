package hodau.backendapi.com.shopnoithat.responsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hodau.backendapi.com.shopnoithat.entity.UserAccount;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

    UserAccount getUserByUsername(String username);
    Optional<UserAccount> findById(Long id);

}