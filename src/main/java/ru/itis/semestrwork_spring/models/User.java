package ru.itis.semestrwork_spring.models;


import jakarta.persistence.*;

import lombok.*;

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
    private String birthDate;
    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String confirmed;

    @OneToOne(mappedBy = "user")
    private TeacherProfile teacherProfile;

    @ManyToOne
    @JoinColumn(name = "file_id")
    private File avatar;


}
