package ru.dpopkov.knowthenics.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.model.User;
import ru.dpopkov.knowthenics.repositories.UserRepository;
import ru.dpopkov.knowthenics.security.CustomUserDetails;

import java.util.function.Supplier;

@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class UserDetailsSDJpaService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsSDJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> exceptionSupplier = () -> new UsernameNotFoundException("Not found");
        User user = userRepository.findUserByUsername(username).orElseThrow(exceptionSupplier);
        return new CustomUserDetails(user);
    }
}
