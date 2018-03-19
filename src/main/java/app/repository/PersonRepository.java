package app.repository;

import app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByFullName(final String fullName);

    long deleteByFullName(final String fullName);

}
