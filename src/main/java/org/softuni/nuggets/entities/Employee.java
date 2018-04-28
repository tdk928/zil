package org.softuni.nuggets.entities;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.softuni.nuggets.contants.Constans.EMPLOYERS_TABLE;
import static org.softuni.nuggets.contants.Constans.UUID;
import static org.softuni.nuggets.contants.Constans.UUID_GENERATOR;

@Entity
@Table(name = EMPLOYERS_TABLE)
public class Employee implements UserDetails {
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String ID = "id";
    private static final String EMPLOYERS_AUTHORITIES = "employers_authorities";
    private static final String EMPLOYEE_ID = "employee_id";
    private static final String ROLE_ID = "role_id";
    private static final String EMPLOYERS_EVENTS = "employers_events";
    private static final String EMPLOYEE_ID1 = "employee_id";
    private static final String EVENT_ID = "event_id";
    private static final String DELETED_ON = "deleted_on";
    @Id
    @GeneratedValue(generator = UUID)
    @GenericGenerator(
            name = UUID,
            strategy = UUID_GENERATOR
    )
    @Column(name = ID, updatable = false, nullable = false)
    private String id;

    @Column(unique = true)
    private String username;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    private double salary;

    @Column
    private String password;

    private boolean isAccountNonExpired;

    private boolean isAccountNonLocked;

    private boolean isCredentialsNonExpired;

    private boolean isEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = EMPLOYERS_AUTHORITIES,
    joinColumns = @JoinColumn(name = EMPLOYEE_ID),
    inverseJoinColumns = @JoinColumn(name = ROLE_ID))
    private Set<Role> authorities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = EMPLOYERS_EVENTS,
            joinColumns = @JoinColumn(name = EMPLOYEE_ID1),
            inverseJoinColumns = @JoinColumn(name = EVENT_ID))
    private List<Event> events;

    @Column(name = DELETED_ON)
    private LocalDate deletedOn;

    public Employee() {
        this.events = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public LocalDate getDeletedOn() {
        return this.deletedOn;
    }

    public void setDeletedOn(LocalDate deletedOn) {
        this.deletedOn = deletedOn;
    }
}
