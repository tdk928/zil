package org.softuni.nuggets.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static org.softuni.nuggets.contants.Constans.ROLE_TABLE;

@Entity
@Table(name = ROLE_TABLE)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    private String authority;

    public Role() {
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public long getId() {
        return this.id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
