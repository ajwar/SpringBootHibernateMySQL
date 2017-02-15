package ru.yandex.ajwar.models;

/**
 * Created by Ajwar on 12.02.2017.
 */

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users")
public class User {

    // Автогенерация id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Length(min = 2)
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @Column(name="online")
    private boolean online;

    @Column(name = "picture")
    private String uri;


    public User() { }

    public User(long id) {
        this.id = id;
    }

    public User(String name, String email, boolean online, String uri) {
        this.name = name;
        this.email = email;
        this.online = online;
        this.uri = uri;
    }


    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "User{" +
                "picture='" + uri + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", online=" + online +
                '}';
    }
}
