package AIdeliverer.com.example.deliverer.controller;

import AIdeliverer.com.example.deliverer.entity.User;
import AIdeliverer.com.example.deliverer.service.UserService;
import AIdeliverer.com.example.deliverer.service.impl.UserServiceImpl;
import AIdeliverer.com.example.deliverer.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")

public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private SecurityService service;

//    @Autowired
//    private UserValidator userValidator;
//
//    @Autowired
//    private SecurityService securityService;

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();

        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }

        return "redirect:/login?logout";
    }


    @GetMapping
    public List<User> getUser() {
        return userService.getUsers();
    }

    @PostMapping(path = "register")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userid") Long userId,
            User user
    ) {
        userService.updateUser(user);
    }

}
