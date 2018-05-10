package pl.coderslab.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.springsecurity.model.UserAuthoritory;


public interface UserAuthoritoryRepository extends JpaRepository<UserAuthoritory, Long> {
}
