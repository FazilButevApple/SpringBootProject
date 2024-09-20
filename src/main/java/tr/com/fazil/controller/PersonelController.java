package tr.com.fazil.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.fazil.exceptions.CustomResponse;
import tr.com.fazil.data.dto.PersonelDto;
import tr.com.fazil.data.projection.PersonelBilgileriProjection;
import tr.com.fazil.service.PersonelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/personels")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
public class PersonelController {

    private final PersonelService personelService;


    @PostMapping
    public CustomResponse<PersonelDto> createUserNormal(@Valid @RequestBody PersonelDto personelDto) {
            PersonelDto createdPersonel = personelService.createPersonel(personelDto);
            return new CustomResponse<>("Personel başarıyla oluşturuldu.", "SUCCESS_200", createdPersonel);
    }

    @PostMapping("/sequence")
    public List<PersonelDto> createUserSequence(@RequestBody PersonelDto personelDto) {
        List<PersonelDto> savedPersonels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            PersonelDto savedUser = personelService.createPersonel(personelDto);
            savedPersonels.add(savedUser);
        }
        return savedPersonels;
    }

    @GetMapping
    public ResponseEntity<Page<PersonelDto>> getAllPersonels(Pageable pageable) {
        return ResponseEntity.ok(personelService.getAllPersonels(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonelDto> getPersonelById(@PathVariable Integer id) {
        PersonelDto personelDto = personelService.getPersonelById(id);
        if (Objects.isNull(personelDto)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personelDto);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePersonelById(@PathVariable Integer id) {
        personelService.deletePersonel(id);
    }

    @PutMapping("/{id}")
    public PersonelDto putPersonelById(@PathVariable Integer id, @RequestBody PersonelDto personelDto) {
        return personelService.updatePersonel(id,personelDto);
    }

    @GetMapping("/personel-bilgileri/{personelId}")
    public ResponseEntity<List<PersonelBilgileriProjection>> getPersonelWithMeslekBilgileri(@PathVariable Integer personelId) {
        List<PersonelBilgileriProjection> projectionList = personelService.getPersonelWithMeslekProjection(personelId);
        return ResponseEntity.ok(projectionList);
    }
}
