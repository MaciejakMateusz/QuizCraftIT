package pl.coderslab.zadanie_rekrutacyjne_cl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String answer;

    private boolean correct;

    @ManyToOne
    private Question question;
}
