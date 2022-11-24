package com.example.demoauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_group",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> student_groups = new HashSet<>();
}
