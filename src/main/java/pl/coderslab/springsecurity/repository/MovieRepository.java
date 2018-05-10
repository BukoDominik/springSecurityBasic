package pl.coderslab.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.springsecurity.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
