package ru.itis.semestrwork_spring.services;

import ru.itis.semestrwork_spring.dto.UserAccountForm;

public interface UsersService {
    void updatePassword(String username, String password);
    void deleteUser(String username);
    boolean userExists(String username, String password);
    boolean userExistsByUsername(String username);
    void changeUserData(UserAccountForm form);
    String getPhoneNumber(String username);
}
