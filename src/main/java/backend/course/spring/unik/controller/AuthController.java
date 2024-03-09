package backend.course.spring.unik.controller;

import backend.course.spring.unik.dto.request.LoginRequest;
import backend.course.spring.unik.dto.request.RefreshTokenRequest;
import backend.course.spring.unik.dto.request.RegistrationRequest;
import backend.course.spring.unik.dto.response.AuthenticationResponse;
import backend.course.spring.unik.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth controller",description = "Controller for authentication and registration!")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "For user registration!")
    public String userRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.userRegistration(registrationRequest);
    }

    @PostMapping("/login")
    @Operation(summary = "For user and admin login!")
    public AuthenticationResponse userLogin(@RequestBody LoginRequest loginRequest) {
        return authService.userLogin(loginRequest);
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "Refresh token", description = "Обновляет JWT токен если выданный JWT уже истек, действует 6 часов")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }

    @PostMapping("/admin")
    @Operation(summary = "For admin registration!")
    public String adminRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.adminRegistration(registrationRequest);
    }
}
