package com.proyecto.abanca.security.controllers;

import com.proyecto.abanca.model.user.User;
import com.proyecto.abanca.repositories.user.UserRepository;
import com.proyecto.abanca.model.user.Role;
import com.proyecto.abanca.repositories.user.RoleRepository;
import com.proyecto.abanca.security.security.jwt.JwtUtils;
import com.proyecto.abanca.model.user.ERole;
import com.proyecto.abanca.security.payload.request.LoginRequest;
import com.proyecto.abanca.security.payload.request.SignupRequest;
import com.proyecto.abanca.security.payload.response.JwtResponse;
import com.proyecto.abanca.security.payload.response.MessageResponse;
import com.proyecto.abanca.security.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/abanca/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(JwtResponse
                .builder()
                .token(jwt)
                .username(userDetails.getUsername())
                .id(userDetails.getId())
                .roles(roles)
                .build());

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role accountHolderRole = roleRepository.findByName(ERole.ROLE_ACCOUNTHOLDER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(accountHolderRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "thirdparty":
                        Role thirdPartyRole = roleRepository.findByName(ERole.ROLE_THIRDPARTY)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(thirdPartyRole);

                        break;
                    default:
                        Role accountHolderRole = roleRepository.findByName(ERole.ROLE_ACCOUNTHOLDER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(accountHolderRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
