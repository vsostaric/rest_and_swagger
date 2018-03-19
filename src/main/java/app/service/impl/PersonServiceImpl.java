package app.service.impl;

import app.model.Person;
import app.repository.PersonRepository;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
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

    @Override
    @Transactional
    public Long removePerson(String fullName) {
        return personRepository.deleteByFullName(fullName);
    }
}
