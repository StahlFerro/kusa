package io.stahlferro.kusa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@RequiredArgsConstructor
@Getter @Setter @ToString
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final Date expirationDate;
}
