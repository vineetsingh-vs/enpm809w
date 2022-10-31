package com.umd.hrm.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="compensation")
public class Compensation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "prev_salary")
    private Long prevSalary;

    @Column(name = "updated_salary")
    private Long updatedSalary;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "increment_salary")
    private Long incrementSalary;

    private String prerequisites;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Account account;
}
