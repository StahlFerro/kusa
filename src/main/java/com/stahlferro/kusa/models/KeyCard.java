package com.stahlferro.kusa.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class KeyCard {
//    @Autowired
//    private RandomService randomService;

    @Id
    @GeneratedValue(generator = "keycard-generator")
    @GenericGenerator(
            name = "keycard-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "keycard_sequence")
            }
    )
    private long id;

    @NotEmpty(message = "Name must not be empty!")
    private String name;
    private int accessLevel;

    private String uuid;

//    public KeyCard () {
//        this.uuid = randomService.generateRandomUUID();
//    }

    public long getId() {
        return id;
    }

    public String geFtName() {
        return name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "KeyCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accessLevel=" + accessLevel +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

