package ma.ensa.researchgate.web;


import ma.ensa.researchgate.dto.LogoutResponse;
import ma.ensa.researchgate.dto.RegisterRequest;
import ma.ensa.researchgate.dto.RegisterResponse;
import ma.ensa.researchgate.entities.User;
import ma.ensa.researchgate.exceptions.UserAlreadyExistAuthenticationException;
import ma.ensa.researchgate.service.serviceimpl.RefreshTokenService;
import ma.ensa.researchgate.service.serviceimpl.Userserviceimpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final Userserviceimpl authService;
    private final RefreshTokenService refreshTokenService;
    private final Userserviceimpl userserviceimpl;

    @DeleteMapping("/logout")
    public ResponseEntity<LogoutResponse> logout() {
        User currentUser = userserviceimpl.getCurrentUser();
        LogoutResponse response = new LogoutResponse();
        response.setEmail(currentUser.getEmail());
        refreshTokenService.deleteRefreshToken(currentUser.getEmail());
        response.setMessage("Logged out successfully! x)");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
