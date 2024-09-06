package tr.com.fazil.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import tr.com.fazil.helper.Alphabetic;
import tr.com.fazil.helper.MaxSpaces;

import java.io.Serializable;

@Getter
@Setter
public class PersonelDto implements Serializable {

    private Integer id;

    @MaxSpaces(value = 3, message = "Ad alanı en fazla {value} boşluk içerebilir.")
    @NotBlank(message = "Ad alanı boş olamaz")
    @Size(min = 3, message = "Ad en az 3 karakter olabilir")
    @Size(max = 100, message = "Ad en fazla 100 karakter olabilir")
    private String ad;


    @Alphabetic
    @NotBlank(message = "Soyad alanı boş olamaz")
    @Size(min = 3, message = "Soyad en az 3 karakter olabilir")
    @Size(max = 100, message = "Soyad en fazla 100 karakter olabilir")
    private String soyad;

    @NotNull(message = "Yaş alanı boş olamaz")
    private Integer yas;

}