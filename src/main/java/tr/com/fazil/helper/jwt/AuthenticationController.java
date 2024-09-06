package tr.com.fazil.helper.jwt;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.dto.LoginKullaniciDto;
import tr.com.fazil.data.entity.Kullanici;
import tr.com.fazil.exceptions.CustomResponse;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public CustomResponse<Kullanici> register(@RequestBody KullaniciDto kullaniciDto) {
        Kullanici registeredKullanici = authenticationService.signup(kullaniciDto);

        return new CustomResponse<>("Kullanıcı başarıyla oluşturuldu.", "SCSS_200", registeredKullanici);
    }

    @PostMapping("/login")
    public CustomResponse<LoginResponse> authenticate(@RequestBody LoginKullaniciDto loginKullaniciDto) {
        Kullanici authenticatedUser = authenticationService.authenticate(loginKullaniciDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return new CustomResponse<>("Kullanıcı login işlemi başarıyla yapıldı.","LGN_200",loginResponse);
    }

    @GetMapping("/authCheck")
    public CustomResponse<Kullanici> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Kullanici currentKullanici = (Kullanici) authentication.getPrincipal();

        return new CustomResponse<>("Kullanıcı authCheck işlemi yapıldı.","AUTH_200",currentKullanici);
    }
}

