package io.stahlferro.kusa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter @ToString
public class UserBase {
    @Id
    @GeneratedValue(generator = "user-generator")
    @GenericGenerator(
            name = "user-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence")
            }
    )
    private long id;

    @Column(unique = true, nullable = false)
    private String loginName;

    private String passwordHash;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Email must not be empty")
    private String email;
}
