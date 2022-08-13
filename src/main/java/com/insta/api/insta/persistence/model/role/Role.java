package com.insta.api.insta.persistence.model.role;

import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "roleId")
    private List<User> users;
}
