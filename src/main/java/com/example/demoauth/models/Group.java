package com.example.demoauth.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "group")
@Data
@NoArgsConstructor
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "name")
    private String name;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;


}
