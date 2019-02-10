package com.shiren.sjwt.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter
@Table(name = "s_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue
    private Long pid;

    @Column(name = "s_permission_discrible")
    private String permission;

    public Permission() {}

    public Permission(String permission) {
        this.permission = permission;
    }
}
