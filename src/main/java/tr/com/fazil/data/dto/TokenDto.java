package tr.com.fazil.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class TokenDto {

    private String token;

    private String uuid;

    private String subUuid;
}
