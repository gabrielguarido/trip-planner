package com.trip.planner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plan", uniqueConstraints = {@UniqueConstraint(columnNames = {"country_name"})})
public class Plan {
    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    @Column(nullable = false, length = 100, unique = true, name = "country_name")
    private String countryName;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate departureDate;

    @NotNull
    @Column(nullable = false)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate returnDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "plan_expense", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "expense_id"))
    private Set<Expense> expenseSet = new HashSet<>();
}
