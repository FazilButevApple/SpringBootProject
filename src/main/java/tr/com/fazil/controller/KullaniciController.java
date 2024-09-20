package tr.com.fazil.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAuthority('USER')")
    public CustomResponse<List<Kullanici>> allUsers() {
        List<Kullanici> users = kullaniciService.allUsers();
        return new CustomResponse<>("Kullanıcı listesi başarıyla çekildi.","SPR_201",users);
    }
}
