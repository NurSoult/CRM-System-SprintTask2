package kz.bitlab.springsprinttask2.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "requests")
@Getter
@Setter

public class ApplicationRequest {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "id") //Column Name
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "phone")
    private String phone;

    @Column(name = "handled")
    private boolean handled;

    @ManyToOne
    private Courses course;


}
