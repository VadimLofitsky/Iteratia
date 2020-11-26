package lofitsky.iteratia.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Дата установления курсов валют из ответа сервиса ЦБ (атрибут Date корневого элемента ValCurs).
 */

@Data
@Entity
@Table(name = "update")
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    Date date;
}