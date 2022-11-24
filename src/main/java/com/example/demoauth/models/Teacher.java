package com.example.demoauth.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_group",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> teacher_groups = new HashSet<>();
}
