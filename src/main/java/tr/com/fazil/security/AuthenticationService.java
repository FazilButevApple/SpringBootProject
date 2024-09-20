package tr.com.fazil.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.dto.LoginKullaniciDto;
import tr.com.fazil.data.entity.Kullanici;
import tr.com.fazil.data.mapper.KullaniciMapper;
import tr.com.fazil.repository.KullaniciRepository;

import java.util.UUID;

@Service
public class AuthenticationService {

    private final KullaniciRepository kullaniciRepository;

    private final PasswordEncoder passwordEncoder;

    private final KullaniciMapper kullaniciMapper;

    public AuthenticationService(
            KullaniciRepository kullaniciRepository,
            PasswordEncoder passwordEncoder,
            KullaniciMapper kullaniciMapper
    ) {
        this.kullaniciRepository = kullaniciRepository;
        this.passwordEncoder = passwordEncoder;
        this.kullaniciMapper = kullaniciMapper;
    }

    public KullaniciDto signup(KullaniciDto kullaniciDto) {
        kullaniciDto.setUuid(UUID.randomUUID());
        Kullanici kullanici = kullaniciMapper.toEntity(kullaniciDto);
        kullanici.setPassword(passwordEncoder.encode(kullaniciDto.getPassword()));
        kullaniciRepository.save(kullanici);
        return kullaniciDto;
    }

    public KullaniciDto authenticate(LoginKullaniciDto loginKullaniciDto) {
        return kullaniciMapper.toDto(kullaniciRepository.findByEmail(loginKullaniciDto.getEmail())
                .orElseThrow());
    }
}
