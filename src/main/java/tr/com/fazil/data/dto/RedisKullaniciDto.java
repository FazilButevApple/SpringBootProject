package tr.com.fazil.data.dto;

import lombok.Getter;
import lombok.Setter;
import tr.com.fazil.data.entity.Kullanici;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RedisKullaniciDto implements Serializable {

    private KullaniciDto kullaniciDto;

    private String subUuid;

    private List<KullaniciYetki> yetkiList;
}
