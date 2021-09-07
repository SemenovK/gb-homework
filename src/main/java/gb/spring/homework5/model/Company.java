package gb.spring.homework5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Data
@AllArgsConstructor

@NamedQueries({
        @NamedQuery(name = "findByName", query = "select c from Company c where companyName = :name")
})
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String companyName;


}
