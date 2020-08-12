package ru.nsu.truckcomp.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.truckcomp.usermanagement.entity.*;
import ru.nsu.truckcomp.usermanagement.repository.*;
import java.util.Collections;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerNewUserAccount(String email, String password) {
        if (emailExists(email)) {
            return null;
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        userRepository.save(user);
        return user;
    }

    public void deleteUser(User user) {
        PasswordResetToken passwordToken = passwordTokenRepository.findByUser(user);
        if (passwordToken != null) {
            passwordTokenRepository.delete(passwordToken);
        }
        userRepository.delete(user);
    }

    public void createPasswordResetToken(User user, String token) {
        PasswordResetToken pwdToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(pwdToken);
    }

    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
