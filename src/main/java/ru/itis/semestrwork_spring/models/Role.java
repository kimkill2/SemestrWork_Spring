package ru.itis.semestrwork_spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Role {
    STUDENT("Ученик"),
    TEACHER("Учитель"),
    ADMIN("Админ");

    private final String name;


    public static Role getRole(String name) {
        for (Role role : Role.values()) {
            if (role.getName().equals(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}
