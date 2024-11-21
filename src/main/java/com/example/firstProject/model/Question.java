package com.example.firstProject.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String questiontitle ;
    private String option1 ;
    private String option2 ;
    private String option3 ;
    private String option4 ;
    private String difficultylevel ;
    private String rightanswer ;
    private String category;

}
