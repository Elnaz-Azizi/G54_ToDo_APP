package se.lexicon.todo_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
//@Builder // Builder Design Pattern
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 150)
    private String email;

}
