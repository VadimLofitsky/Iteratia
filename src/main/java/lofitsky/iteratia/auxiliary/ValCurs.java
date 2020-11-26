package lofitsky.iteratia.auxiliary;

import lofitsky.iteratia.model.Currency;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Представление структуры XML в ответе сервиса ЦБ с курсами валют с использованием класса {@link Currency}.
 */

@Data
@XmlRootElement(name = "ValCurs")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValCurs {
    @XmlAttribute(name = "Date")
    String date;

    @XmlAttribute
    String name;

    @XmlElement(name = "Valute")
    List<Currency> valutes;
}