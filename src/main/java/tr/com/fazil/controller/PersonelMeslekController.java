package tr.com.fazil.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.fazil.data.entity.PersonelMeslek;
import tr.com.fazil.service.PersonelMeslekService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personel-meslek")
public class PersonelMeslekController {

    private final PersonelMeslekService personelMeslekService;

    @GetMapping
    public ResponseEntity<Page<PersonelMeslek>> getPersonelMeslek(Pageable pageable) {
        return ResponseEntity.ok(personelMeslekService.getAllPersonelMeslek(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonelMeslek> getPersonelMeslek(@PathVariable("id") Integer id) {
        return personelMeslekService.getPersonelMeslek(id);
    }

    @PostMapping
    public PersonelMeslek save(@RequestBody PersonelMeslek personelMeslek) {
        return personelMeslekService.savePersonelMeslek(personelMeslek);
    }

    @DeleteMapping("/{id}")
    public void deletePersonelMeslek(@PathVariable("id") Integer id) {
        personelMeslekService.deletePersonelMeslek(id);
    }
}
