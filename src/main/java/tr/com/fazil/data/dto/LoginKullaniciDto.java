package tr.com.fazil.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class LoginKullaniciDto implements Serializable {

    private String email;

    private String password;
}
