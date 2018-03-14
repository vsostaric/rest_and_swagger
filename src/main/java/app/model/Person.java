package app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String address;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "number_of_visits")
    private int numberOfVisits;

    @Column(name = "money_on_his_mind")
    private BigDecimal moneyOnHisMind;

    @Transient
    private Car car;

}
