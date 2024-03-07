package backend.course.spring.unik.service;

import backend.course.spring.unik.dto.request.LoginRequest;
import backend.course.spring.unik.dto.request.RefreshTokenRequest;
import backend.course.spring.unik.dto.request.RegistrationRequest;
import backend.course.spring.unik.dto.response.AuthenticationResponse;
import backend.course.spring.unik.entity.User;
import backend.course.spring.unik.entity.enums.Role;
import backend.course.spring.unik.exception.NotFoundException;
import backend.course.spring.unik.exception.TokenExpiredException;
import backend.course.spring.unik.exception.UnauthorizedException;
import backend.course.spring.unik.exception.UserAlreadyExistException;
import backend.course.spring.unik.repository.UserRepository;
import backend.course.spring.unik.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String userRegistration(RegistrationRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с email: " + request.getEmail() + " уже существует!");
        }

        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с username: " + request.getUsername() + " уже существует!");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);

        return "Регистрация прошла успешно!";
    }

    public AuthenticationResponse userLogin(LoginRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден!"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword()) && user.getUsername().equals(request.getUsername())) {
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new UnauthorizedException("Данные введены неправильно, повторите попытку!");
        }
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Такой пользователь не сущуествует!"));

        if (jwtService.isTokenValid(request.getToken(), user)) {
            return AuthenticationResponse.builder()
                    .refreshToken(jwtService.generateRefreshToken(user))
                    .accessToken(jwtService.generateToken(user))
                    .build();
        } else {
            throw new TokenExpiredException("Время токена истекло!");
        }
    }
}
