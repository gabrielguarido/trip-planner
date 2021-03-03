package com.trip.planner.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "[user]", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @Column(name = "user_id")
    private Integer id;

    @NotEmpty
    @Size(max = 40)
    @Column(nullable = false, length = 40)
    private String firstName;

    @Size(max = 40)
    @Column(length = 40)
    private String lastName;

    @Email
    @NotEmpty
    @Size(max = 140)
    @Column(nullable = false, length = 140, unique = true)
    private String email;

    @NotEmpty
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_plan", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "plan_id"))
    private Set<Plan> planSet = new HashSet<>();
}
