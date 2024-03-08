package bookmyshow.serviceApp.Repositories;

import bookmyshow.serviceApp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from users where age > :val",nativeQuery = true)
    List<User> findAllAboveAge(Integer val);
}
