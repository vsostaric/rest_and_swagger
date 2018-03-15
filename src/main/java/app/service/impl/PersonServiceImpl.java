package app.service.impl;

import app.model.Person;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPerson(Integer id) {
        return personRepository.findById(id);
    }

    @Override
    public Optional<Person> getPersonByFullName(String fullName) {
        return personRepository.findByFullName(fullName);
    }

    @Override
    public List<Person> getPersons(int page, int size) {
        return personRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
