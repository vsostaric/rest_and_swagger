package app;

import app.model.Person;
import app.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Test
    public void testFindByFullName() {

        Optional<Person> result = personRepository.findByFullName("Hightop Straw");
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void testInsertPerson() {

        final String testName = "Test name";
        assertThat(personRepository.findByFullName(testName).isPresent()).isFalse();

        final Person person = PersonTest.getTestPerson();
        person.setFullName(testName);
        personRepository.save(person);


        assertThat(personRepository.findByFullName(testName).isPresent()).isTrue();

    }

}
