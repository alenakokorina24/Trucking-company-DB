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

    public User registerNewUserAccount(final UserDto accountDto) {
//        if (emailExists(accountDto.getEmail())) {
//            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
//        }
        User user = new User();

        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
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

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
