package com.fbaron.data.jpa.user.entity;

import com.fbaron.data.jpa.phone.entity.PhoneEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class UserEntity {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneEntity> phones;

}
