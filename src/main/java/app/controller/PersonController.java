package app.controller;

import app.mapper.PersonMapper;
import app.model.Person;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService personService;

    private PersonMapper personMapper;

    @Autowired
    public PersonController(final PersonService personService,
                            final PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping("/getPersonByFullName/{fullName}")
    public Person getPerson(final @PathVariable("fullName") String fullName) {
        return personService.getPersonByFullName(fullName).orElseThrow(() -> new RuntimeException("Person not found with name: " + fullName));
    }

}
