package demo.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id

    UUID userId;

    @Column(name = "name",nullable = false)
    String userName;

    @Column(name = "email",nullable = false)
    String email;

    //getter ve setter lombok ile
}

