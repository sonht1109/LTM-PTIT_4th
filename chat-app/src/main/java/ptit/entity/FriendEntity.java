package ptit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="friend")
public class FriendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date created_at;

    @LastModifiedDate
    private Date updated_at;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "blocking_id")
    private Long blocking_id;

    @JoinColumn(name = "user_id_1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity user_id_1;

    @JoinColumn(name = "user_id_2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity user_id_2;

}
