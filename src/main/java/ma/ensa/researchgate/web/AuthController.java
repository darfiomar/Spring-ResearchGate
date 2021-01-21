package ma.ensa.researchgate.web;

import ma.ensa.researchgate.dto.*;
import ma.ensa.researchgate.entities.User;
import ma.ensa.researchgate.exceptions.UserAlreadyExistAuthenticationException;
import ma.ensa.researchgate.service.serviceimpl.RefreshTokenService;
import ma.ensa.researchgate.service.serviceimpl.Userserviceimpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final Userserviceimpl authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Logged in! x)");
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<AuthenticationResponse> refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        System.out.println("Token refreshed! x)");
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }

}
