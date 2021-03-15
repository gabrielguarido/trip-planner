package com.trip.planner.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "plan_expense", joinColumns = @JoinColumn(name = "plan_id"), inverseJoinColumns = @JoinColumn(name = "expense_id"))
    private Set<Expense> expenseSet = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    private Integer duration;

    @JsonIgnore
    @ManyToMany(mappedBy = "planSet")
    private Set<User> users = new HashSet<>();

    @PreRemove
    private void removeProfiles() {
        for (User user : users) {
            user.getPlanSet().remove(this);
        }
    }
}
