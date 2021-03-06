package com.shiren.sjwt.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "s_role")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long rid;

    @Column(name = "s_role_name")
    private String roleName;

    @ManyToMany
    @JoinTable(name = "s_user_role", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = {
            @JoinColumn(name = "uid") })
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "s_role_permission", joinColumns = { @JoinColumn(name = "rid") }, inverseJoinColumns = {
            @JoinColumn(name = "pid") })
    private List<Permission> permissions;

    public Role() {}

    public Role(String roleName, List<Permission> permissions) {
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
