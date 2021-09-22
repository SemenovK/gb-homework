package gb.spring.homework11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;
import javax.persistence.*;
import java.math.BigInteger;

@Entity(name = "Score")
@Table(name = "Score")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Score {

    @Column(name = "user_id")
    @Id
    BigInteger userId;

    @Column(name = "user_score")
    int score;

}
