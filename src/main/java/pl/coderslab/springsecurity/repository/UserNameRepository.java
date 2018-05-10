package pl.coderslab.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.springsecurity.model.UserName;

public interface UserNameRepository extends JpaRepository<UserName, Long> {
}
