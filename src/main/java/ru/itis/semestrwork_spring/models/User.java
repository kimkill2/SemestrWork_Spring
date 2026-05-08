package ru.itis.semestrwork_spring.models;


import jakarta.persistence.*;

import lombok.*;
import tools.jackson.databind.DatabindException;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String confirmed;
    private String confirmCode;
    @Column(unique = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private TeacherProfile teacherProfile;


    @ManyToOne
    @JoinColumn(name = "file_id")
    @ToString.Exclude
    private File avatar;


}
