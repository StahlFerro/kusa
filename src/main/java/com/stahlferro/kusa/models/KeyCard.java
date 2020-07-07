package com.stahlferro.kusa.models;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
}
