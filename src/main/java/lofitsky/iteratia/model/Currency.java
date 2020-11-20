package lofitsky.iteratia.model;

import lofitsky.iteratia.util.XMLStringFloatAdapter;
import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Data
@Entity
@Table(name = "currency")
@XmlRootElement(name = "Valute")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

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

    @Column(nullable = false, columnDefinition = "Numeric(10, 4)")
    @XmlElement(name = "Value")
    @XmlJavaTypeAdapter(XMLStringFloatAdapter.class)
    Float value;
}