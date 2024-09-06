package tr.com.fazil.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class KullaniciDto implements Serializable {

    private Integer id;

    private String ad;

    private String soyad;

    private String email;

    private String password;

    private UUID uuid;
}
