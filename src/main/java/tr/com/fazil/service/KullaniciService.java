package tr.com.fazil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.entity.Kullanici;
import tr.com.fazil.data.mapper.KullaniciMapper;
import tr.com.fazil.repository.KullaniciRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;
    private final KullaniciMapper kullaniciMapper;

    public KullaniciDto createKullanici(KullaniciDto kullaniciDto) {
        kullaniciDto.setUuid(UUID.randomUUID());
        Kullanici kullanici = kullaniciMapper.toEntity(kullaniciDto);
        kullanici = kullaniciRepository.save(kullanici);
        return kullaniciMapper.toDto(kullanici);
    }


    @Cacheable(value = "kullanici", key = "#id")
    public KullaniciDto getKullaniciById(Integer id) {
        Kullanici kullanici = kullaniciRepository.findById(id).orElse(null);
        return kullaniciMapper.toDto(kullanici);
    }


    public Page<KullaniciDto> getAllKullanici(Pageable pageable) {
        return kullaniciRepository.findAll(pageable).map(kullaniciMapper::toDto);
    }

    public KullaniciDto updatePersonel(Integer id, KullaniciDto kullaniciDto) {
        Kullanici kullanici = kullaniciRepository.findById(id).orElseThrow();
        Kullanici kullanici1 = kullaniciMapper.toEntity(kullaniciDto);
        if (kullanici.getId().equals(kullaniciDto.getId())) {
            Kullanici kullanici2 = kullaniciRepository.save(kullanici1);
            return kullaniciMapper.toDto(kullanici2);
        } else {
            throw new RuntimeException();
        }

    }

    public void deleteKullanici(Integer id) {
        kullaniciRepository.deleteById(id);
    }


    public List<Kullanici> allUsers() {
        List<Kullanici> kullanicilar = new ArrayList<>();

        kullaniciRepository.findAll().forEach(kullanicilar::add);

        return kullanicilar;
    }
}
