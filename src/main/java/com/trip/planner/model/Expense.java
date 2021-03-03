package com.trip.planner.model;

import com.trip.planner.enumerator.Currency;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @Column(name = "expense_id")
    private Integer id;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated
    private Currency currency;

}
