package bookmyshow.serviceApp.Repositories;

import bookmyshow.serviceApp.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MovieRepository extends JpaRepository<Movie,Integer> {


    Optional<Movie> findByMovieName(String movieName);
}
