package com.insta.api.insta.persistence.model.follower;

import com.insta.api.insta.persistence.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

  //  @ManyToOne
  //  @JoinColumn(name="from_user_fk")
  //  private User from;

  //  @ManyToOne
  //  @JoinColumn(name="to_user_fk")
  //  private User to;

}
