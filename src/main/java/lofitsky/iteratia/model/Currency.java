package lofitsky.iteratia.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false)
    String rId;

    @Column(unique = true, nullable = false)
    int numCode;

    @Column(unique = true, nullable = false)
    String charCode;

    @Column(nullable = false)
    int nominal;

    @Column(unique = true, columnDefinition = "text", nullable = false)
    String name;

    @Column(nullable = false, columnDefinition = "Numeric(10, 4)")
    float value;

}