package lofitsky.iteratia.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/** Класс для описания операции конвертации.<p>
 * Оперция в истории описывается следующими полями:<p>
 * <pre>
 * <i>id</i> - идентификатор в базе данных
 * <i>date</i> - дата операции
 * <i>currencyFrom, currencyTo</i> - объекты {@link Currency}, валюты из какой в какую
 * <i>rate</i> - курс по отношению друг у другу
 * <i>amount</i> - объём конвертированной currencyFrom
 * </pre>
 */

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