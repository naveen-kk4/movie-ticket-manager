package bookmyshow.serviceApp.Repositories;

import bookmyshow.serviceApp.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TheatreRepository extends JpaRepository<Theatre,Integer> {
    Theatre findByLocation(String location);

    Optional<Theatre> findByName(String theatreName);
}
