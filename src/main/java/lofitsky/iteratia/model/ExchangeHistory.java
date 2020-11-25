package lofitsky.iteratia.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "exchange_history")
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    LocalDateTime date;

    @OneToOne
    @JoinColumn(referencedColumnName = "numCode", nullable = false)
    Currency currencyFrom;

    @OneToOne
    @JoinColumn(referencedColumnName = "numCode", nullable = false)
    Currency currencyTo;

    @Column(columnDefinition = "Numeric(20, 10)", nullable = false)
    float rate;

    @Column(columnDefinition = "Numeric(20, 10)", nullable = false)
    float amount;
}