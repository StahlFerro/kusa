package io.stahlferro.kusa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Entity
@Getter @Setter @ToString
public class Keycard extends BasePrimaryModel {
//    @Autowired
//    private RandomService randomService;

    @NotEmpty(message = "Name must not be empty!")
    private String name;

    private int accessLevel;

//    private String lockWiseInfo;

//    public KeyCard () {
//        this.uuid = randomService.generateRandomUUID();
//    }

//    public long getId() {
//        return id;
//    }
//
//    public String geFtName() {
//        return name;
//    }
//
//    public int getAccessLevel() {
//        return accessLevel;
//    }
//
//    public String getUuid() {
//        return uuid;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAccessLevel(int accessLevel) {
//        this.accessLevel = accessLevel;
//    }
//
//    public void setUuid(String uuid) {
//        this.uuid = uuid;
//    }
//
//    @Override
//    public String toString() {
//        return "KeyCard{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", accessLevel=" + accessLevel +
//                ", uuid='" + uuid + '\'' +
//                '}';
//    }
}

