package app;

import app.model.Car;
import app.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PersonTest {

    public static Person getTestPerson() {
        return Person.builder()
                .fullName("Hightop Straw")
                .address("Pennbroke 82")
                .dateOfBirth(LocalDate.of(1989, 10, 13))
                .numberOfVisits(7)
                .moneyOnHisMind(new BigDecimal("1021.44"))
                .car(Car.builder().carModel("Renault Laguna").build())
                .build();

    }

}
