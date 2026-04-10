package ru.itis.semestrwork_spring.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.semestrwork_spring.models.User;
import ru.itis.semestrwork_spring.repositories.UsersRepository;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return new CustomUserDetailsImpl(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
