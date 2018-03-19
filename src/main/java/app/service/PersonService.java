package app.service;

import app.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> getPersonByFullName(final String fullName);

    List<Person> getPersons(int page, int size);

    Person savePerson(final Person person);

    Long removePerson(final String fullName);

}
