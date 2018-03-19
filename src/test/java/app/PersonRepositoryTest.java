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
    public static final String TEST_NAME = "Test name";

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

        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isFalse();
        final Person person = saveTestPerson();

        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isTrue();

        personRepository.delete(person);
        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isFalse();

    }

    @Test
    public void testRemovePersonByName() {

        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isFalse();
        final Person person = saveTestPerson();

        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isTrue();

        long deleted = personRepository.deleteByFullName(person.getFullName());

        assertThat(deleted).isEqualTo(1);
        assertThat(personRepository.findByFullName(TEST_NAME).isPresent()).isFalse();

    }

    private Person saveTestPerson() {
        final Person person = PersonTest.getTestPerson();
        person.setFullName(TEST_NAME);

        personRepository.save(person);
        return person;
    }

}
