package com.anassifi.fixmycity.controllers;

import com.anassifi.fixmycity.models.User;
import com.anassifi.fixmycity.services.RoleService;
import com.anassifi.fixmycity.services.UDService;
import com.anassifi.fixmycity.RequestObjects.AuthRequest;
import com.anassifi.fixmycity.services.UserService;
import com.anassifi.fixmycity.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private UDService userDetailsService;
    private UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthRequest authRequest) {
        try {
            String login = authRequest.getLogin();
            String password = authRequest.getPassword();
            final UserDetails userDetails = userDetailsService.loadUserByUsername(login);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Wrong password!"));
            }
            return ResponseEntity.ok().body(Map.of("token", jwtUtil.generateToken(userDetails), "user", userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "This username already ben taken!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected internal server error!"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody AuthRequest authRequest) {
        String login = authRequest.getLogin();
        String password = authRequest.getPassword();
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRole(roleService.getByName("ROLE_USER"));
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userService.add(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("success", "User created successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "This username already ben taken!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected internal server error!"));
        }
    }
}
