package mapper;

import app.Application;
import app.mapper.PersonMapper;
import app.model.Car;
import app.model.Person;
import app.model.PersonDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PersonMapperTest {

    private Person person_hightop;
    private Person person_laydown;
    private Person person_run;

    private PersonDTO person_hightop_mapped;
    private Person person_hightop_remapped;

    private PersonMapper personMapper;

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Before
    public void init() {
        person_hightop = Person.builder()
                .fullName("Hightop Straw")
                .address("Pennbroke 82")
                .dateOfBirth(LocalDate.of(1989, 10, 13))
                .numberOfVisits(7)
                .moneyOnHisMind(new BigDecimal("1021.44"))
                .car(Car.builder().carModel("Renault Laguna").build())
                .build();

        person_laydown = Person.builder()
                .fullName("Laydown Relax")
                .address("Wullford Road 23")
                .dateOfBirth(LocalDate.of(1988, 5, 8))
                .numberOfVisits(23)
                .build();

        person_run = Person.builder()
                .fullName("Run Vigorously")
                .address("Someville 7")
                .dateOfBirth(LocalDate.of(1961, 1, 24))
                .numberOfVisits(115)
                .build();

        person_hightop_mapped = personMapper.personToPersonDTO(person_hightop);
        person_hightop_remapped = personMapper.personDTOToPerson(person_hightop_mapped);
    }

    @Test
    public void testAddressMapping() {
        assertThat(person_hightop_mapped.getResidence()).isEqualTo(person_hightop.getAddress());
    }

    @Test
    public void testAddressDefaultMapping() {
        person_hightop.setAddress(null);
        final PersonDTO mapped = personMapper.personToPersonDTO(person_hightop);
        assertThat(mapped.getResidence()).isEqualTo("unknown");
    }

    @Test
    public void testName() {
        assertThat(person_hightop_mapped.getFirstName()).isEqualTo("Hightop");
        assertThat(person_hightop_mapped.getLastName()).isEqualTo("Straw");
    }

    @Test
    public void testYearOfBirth() {
        assertThat(person_hightop_mapped.getBorn()).isEqualTo("1989 13 10");
    }

    @Test
    public void testIntToLongAndBack() {
        assertThat(person_hightop_mapped.getTimesVisited()).isEqualTo(7);
        assertThat(person_hightop_remapped.getNumberOfVisits()).isEqualTo(7);
    }

    @Test
    public void testMoney() {
        assertThat(person_hightop_mapped.getMoney()).isEqualTo("1.02E3");
        assertThat(person_hightop_remapped.getMoneyOnHisMind().compareTo(new BigDecimal(1.02E+3))).isEqualTo(0);
    }

    @Test
    public void testListMapping() {
        List<Person> personList = new ArrayList<>();
        personList.add(person_hightop);
        personList.add(person_laydown);
        personList.add(person_run);

        List<PersonDTO> mapped = personMapper.personsToPersonDTOs(personList);

        assertThat(mapped.size()).isEqualTo(3);
        assertThat(mapped.get(0).getFirstName()).isEqualTo("Hightop");
        assertThat(mapped.get(1).getFirstName()).isEqualTo("Laydown");
        assertThat(mapped.get(2).getFirstName()).isEqualTo("Run");
    }

    @Test
    public void testSetMapping() {
        Set<Person> personSet = new HashSet<>();
        personSet.add(person_hightop);
        personSet.add(person_laydown);
        personSet.add(person_run);

        Set<PersonDTO> mapped = personMapper.personsToPersonDTOs(personSet);
        assertThat(mapped.size()).isEqualTo(3);

    }

    @Test
    public void testCarMapping() {
        assertThat(person_hightop_mapped.getCarDTO().getModelOfCar()).isEqualTo("Renault Laguna");
    }

}
