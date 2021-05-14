package ru.dpopkov.knowthenics.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.dpopkov.knowthenics.model.Authority;
import ru.dpopkov.knowthenics.model.EncryptionAlgorithm;
import ru.dpopkov.knowthenics.model.User;
import ru.dpopkov.knowthenics.repositories.UserRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Authenticating with wrong user")
    void loggingInWithWrongUser() throws Exception {
        mvc.perform(formLogin())
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("Skip authentication and test the controller method")
    @WithMockUser(username = "user", password = "12345")
    public void skipAuthenticationTest() throws Exception {
        mvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")));
    }

    @Test
    @DisplayName("Test authentication with valid user")
    public void testAuthenticationWithValidUser() throws Exception {
        User mockUser = new User();
        mockUser.setUsername("mockUser");
        mockUser.setPassword(bCryptPasswordEncoder.encode("123"));
        mockUser.setAlgorithm(EncryptionAlgorithm.BCRYPT);

        Authority a = new Authority();
        a.setName("read");
        a.setUser(mockUser);
        mockUser.addAuthority(a);

        when(userRepository.findUserByUsername("mockUser")).thenReturn(Optional.of(mockUser));

        mvc.perform(formLogin().user("mockUser").password("123"))
                .andExpect(authenticated());
    }

    @Test
    @DisplayName("Test authentication with non-existent user")
    public void testAuthenticationWithNonExistentUser() throws Exception {
        when(userRepository.findUserByUsername("nonExistent")).thenReturn(Optional.empty());

        mvc.perform(formLogin()
                .user("nonExistent")
                .password("12345"))
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("Test authentication with invalid password")
    public void testAuthenticationWithInvalidPassword() throws Exception {
        User mockUser = new User();
        mockUser.setUsername("mockUser");
        mockUser.setPassword(bCryptPasswordEncoder.encode("55555"));
        mockUser.setAlgorithm(EncryptionAlgorithm.BCRYPT);

        Authority a = new Authority();
        a.setName("read");
        a.setUser(mockUser);
        mockUser.addAuthority(a);

        when(userRepository.findUserByUsername("mockUser")).thenReturn(Optional.of(mockUser));

        mvc.perform(formLogin().user("mockUser").password("invalid"))
                .andExpect(unauthenticated());
    }
}
