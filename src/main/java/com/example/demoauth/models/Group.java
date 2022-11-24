package com.example.demoauth.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "_group")
@Data
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;


}
