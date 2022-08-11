package com.insta.api.insta.persistence.model.follower;

import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "follows")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

/*
    @ManyToOne
    @JoinColumn(name = "to_user_fk")
    private User to;
*/

}
