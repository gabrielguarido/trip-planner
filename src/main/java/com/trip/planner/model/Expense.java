package com.trip.planner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trip.planner.enumerator.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expense")
public class Expense {
    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false)
    private String description;

    @NotNull
    private Double amount;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @JsonIgnore
    @ManyToMany(mappedBy = "expenseSet")
    private Set<Plan> plans = new HashSet<>();

    @PreRemove
    private void removeProfiles() {
        for (Plan plan : plans) {
            plan.getExpenseSet().remove(this);
            plan.setTotalExpenses(plan.getTotalExpenses() - this.getAmount());
        }
    }
}
