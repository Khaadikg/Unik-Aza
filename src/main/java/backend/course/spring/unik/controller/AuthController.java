package backend.course.spring.unik.controller;

import backend.course.spring.unik.dto.request.LoginRequest;
import backend.course.spring.unik.dto.request.RefreshTokenRequest;
import backend.course.spring.unik.dto.request.RegistrationRequest;
import backend.course.spring.unik.dto.response.AuthenticationResponse;
import backend.course.spring.unik.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String userRegistration(@RequestBody RegistrationRequest registrationRequest) {
        return authService.userRegistration(registrationRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse userLogin(@RequestBody LoginRequest loginRequest) {
        return authService.userLogin(loginRequest);
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "Refresh token", description = "Обновляет JWT токен если выданный JWT уже истек, действует 6 часов")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
