package ru.nsu.truckcomp.usermanagement.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.nsu.truckcomp.usermanagement.entity.User;
import ru.nsu.truckcomp.usermanagement.service.UserDto;
import ru.nsu.truckcomp.usermanagement.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private MessageSource messages;

    @Autowired
    private Environment env;

    @PostMapping("/register/submit")
    @ResponseBody
    public void registerUserAccount(@Valid UserDto accountDto, HttpServletRequest request) {
        userService.registerNewUserAccount(accountDto);
    }

    @PostMapping("/recovery")
    @ResponseBody
    public void resetPassword(final HttpServletRequest request, @RequestParam("email") String userEmail) {
//        final User user = userService.findUserByEmail(userEmail);
//        if (user != null) {
//            final String token = UUID.randomUUID().toString();
//            userService.createPasswordResetToken(user, token);
//            mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
//        }
    }

//    @GetMapping("/user/changePassword")
//    public String showChangePasswordPage(Locale locale, Model model, @RequestParam("token") String token) {
//        final String result = securityUserService.validatePasswordResetToken(token);
//
//        if(result != null) {
//            String message = messages.getMessage("auth.message." + result, null, locale);
//            return "redirect:/login.html?lang=" + locale.getLanguage() + "&message=" + message;
//        } else {
//            model.addAttribute("token", token);
//            return "redirect:/updatePassword.html?lang=" + locale.getLanguage();
//        }
//    }
//
//    @PostMapping("/user/savePassword")
//    @ResponseBody
//    public GenericResponse savePassword(final Locale locale, @Valid PasswordDto passwordDto) {
//
//        final String result = securityUserService.validatePasswordResetToken(passwordDto.getToken());
//
//        if(result != null) {
//            return new GenericResponse(messages.getMessage("auth.message." + result, null, locale));
//        }
//
//        Optional<User> user = userService.getUserByPasswordResetToken(passwordDto.getToken());
//        if(user.isPresent()) {
//            userService.changeUserPassword(user.get(), passwordDto.getNewPassword());
//            return new GenericResponse(messages.getMessage("message.resetPasswordSuc", null, locale));
//        } else {
//            return new GenericResponse(messages.getMessage("auth.message.invalid", null, locale));
//        }
//    }
//
//    @PostMapping("/user/updatePassword")
//    @ResponseBody
//    public void changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
//        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
//        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
//            throw new InvalidOldPasswordException();
//        }
//        userService.changeUserPassword(user, passwordDto.getNewPassword());
//    }

//    private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
//        String url = contextPath + "/user/changePassword?token=" + token;
//        String message = messages.getMessage("message.resetPassword", null, locale);
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setSubject("Reset password");
//        email.setText(message + " \r\n" + url);
//        email.setTo(user.getEmail());
//        email.setFrom(env.getProperty("support.email"));
//        return email;
//    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}