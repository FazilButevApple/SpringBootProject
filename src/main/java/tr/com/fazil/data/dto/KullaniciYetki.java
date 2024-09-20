package tr.com.fazil.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class KullaniciYetki implements GrantedAuthority {

    private String yetki;

    public KullaniciYetki() {
    }

    public KullaniciYetki(String yetki) {
        this.yetki = yetki;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return this.yetki;
    }

    public String getYetki() {
        return yetki;
    }

    public void setYetki(String yetki) {
        this.yetki = yetki;
    }
}
