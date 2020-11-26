package lofitsky.iteratia.model;

import lofitsky.iteratia.auxiliary.XMLStringFloatAdapter;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**<pre>
 * Класс для описания валюты. Поля соответствуют элементам и атрибутам элемента Valute в XML документе сервиса ЦБ РФ
 * по котировкам на текущий день (https://www.cbr.ru/scripts/XML_daily.asp)
 * Например:
 * &lt;Valute ID="R01010"&gt;
 *      &lt;NumCode&gt;<b>036</b>&lt;/NumCode&gt;
 *      &lt;CharCode&gt;<b>AUD</b>&lt;/CharCode&gt;
 *      &lt;Nominal&gt;<b>1</b>&lt;/Nominal&gt;
 *      &lt;Name&gt;<b>Австралийский доллар</b>&lt;/Name&gt;
 *      &lt;Value&gt;<b>55,4875</b>&lt;/Value&gt;
 * &lt;/Valute&gt;<br><br>
 * </pre>
 */

@Data
@Entity
@Table(name = "currency")
// Название элемента в XML-представлении
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false)
    @XmlAttribute(name = "ID")
    String rId;

    @Column(unique = true, nullable = false)
    @XmlElement(name = "NumCode")
    int numCode;

    @Column(unique = true, nullable = false)
    @XmlElement(name = "CharCode")
    String charCode;

    @Column(nullable = false)
    @XmlElement(name = "Nominal")
    int nominal;

    @Column(unique = true, columnDefinition = "text", nullable = false)
    @XmlElement(name = "Name")
    String name;

    // курс валюты в рублях
    @Column(nullable = false, columnDefinition = "Numeric(10, 4)")
    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(XMLStringFloatAdapter.class)
    Float value;
}