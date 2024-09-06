package tr.com.fazil.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.dto.PersonelDto;
import tr.com.fazil.data.entity.Kullanici;
import tr.com.fazil.exceptions.CustomResponse;
import tr.com.fazil.service.KullaniciService;

import java.util.List;

@RestController
@RequestMapping("/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @GetMapping("/")
    public CustomResponse<List<Kullanici>> allUsers() {
        List<Kullanici> users = kullaniciService.allUsers();

        return new CustomResponse<>("Kullanıcı listesi başarıyla çekildi.","SPR_201",users);
    }
}
