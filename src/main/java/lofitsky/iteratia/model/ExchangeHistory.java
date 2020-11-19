package lofitsky.iteratia.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "exchange_history")
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    Date date;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    Currency currencyFrom;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", nullable = false)
    Currency currencyTo;

    @Column(columnDefinition = "Numeric(20, 10)", nullable = false)
    float rate;

    @Column(columnDefinition = "Numeric(20, 10)", nullable = false)
    float amount;
}