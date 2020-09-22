package io.stahlferro.kusa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserBaseDto {
    private String name;
    private String email;
    private String loginName;
    private String password;
}
