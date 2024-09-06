package tr.com.fazil.helper.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.dto.LoginKullaniciDto;
import tr.com.fazil.data.entity.Kullanici;
import tr.com.fazil.repository.KullaniciRepository;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final KullaniciRepository kullaniciRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            KullaniciRepository kullaniciRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Kullanici signup(KullaniciDto input) {
        Kullanici kullanici = new Kullanici();
        kullanici.setAd(input.getAd());
        kullanici.setSoyad(input.getSoyad());
        kullanici.setEmail(input.getEmail());
        kullanici.setPassword(passwordEncoder.encode(input.getPassword()));
        kullanici.setUuid(UUID.randomUUID());

        return kullaniciRepository.save(kullanici);
    }

    public Kullanici authenticate(LoginKullaniciDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return kullaniciRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
