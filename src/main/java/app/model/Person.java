package app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String fullName;
    private String address;
    private LocalDate dateOfBirth;
    private int numberOfVisits;
    private BigDecimal moneyOnHisMind;
    private Car car;

}
