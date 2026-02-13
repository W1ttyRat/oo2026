package ee.tanel.veebipood.repository;

import ee.tanel.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// CrudRepository (min funktsioonid), PagingAndSortingRepository, JpaRepository (kõik võimalikud funktsioonid)

public interface PersonRepository extends JpaRepository<Person,Long> {
    // SELECt * FROM person WHERE email...
    Person findByEmail(String email);
}
