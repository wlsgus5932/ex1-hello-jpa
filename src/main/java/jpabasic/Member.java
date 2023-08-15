package jpabasic;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lab
    private String description;


    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
