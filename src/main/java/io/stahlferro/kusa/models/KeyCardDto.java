package io.stahlferro.kusa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter @Setter @ToString
public class KeyCardDto {
    private String name;
    private int accessLevel;
    private UUID uuid;
//    private String lockWise;
}
