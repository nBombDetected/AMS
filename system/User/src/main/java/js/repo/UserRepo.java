package js.repo;

import js.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByName(String name);
}
