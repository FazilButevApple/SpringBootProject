package tr.com.fazil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.fazil.data.entity.Kullanici;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
    Optional<Kullanici> findByEmail(String email);
}
