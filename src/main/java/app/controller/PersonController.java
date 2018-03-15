package app.controller;

import app.model.Person;
import app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getPersonByFullName/{fullName}")
    public Person getPerson(final @PathVariable("fullName") String fullName) {
        return personService.getPersonByFullName(fullName).orElseThrow(() -> new RuntimeException("Person not found with name: " + fullName));
    }

    @PostMapping(path = "/newPerson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> newPerson(final Person person) {
        return ResponseEntity.ok(personService.savePerson(person));
    }

}
