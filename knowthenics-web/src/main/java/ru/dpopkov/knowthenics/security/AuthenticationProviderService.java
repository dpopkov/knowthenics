package ru.dpopkov.knowthenics.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dpopkov.knowthenics.services.springdatajpa.UserDetailsSDJpaService;

@Slf4j
@Service
@Profile({"spring-data-jpa", "dev", "prod"})
public class AuthenticationProviderService implements AuthenticationProvider {

    private final UserDetailsSDJpaService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    public AuthenticationProviderService(UserDetailsSDJpaService userDetailsService,
                                         BCryptPasswordEncoder bCryptPasswordEncoder,
                                         SCryptPasswordEncoder sCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sCryptPasswordEncoder = sCryptPasswordEncoder;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = authentication.getCredentials().toString();
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
        switch (userDetails.getUser().getAlgorithm()) {
            case BCRYPT:
                return checkPassword(userDetails, rawPassword, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(userDetails, rawPassword, sCryptPasswordEncoder);
            case NOOP:
                log.warn("Using deprecated NoOpPasswordEncoder");
                return checkPassword(userDetails, rawPassword, NoOpPasswordEncoder.getInstance());
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        }
        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }
}
