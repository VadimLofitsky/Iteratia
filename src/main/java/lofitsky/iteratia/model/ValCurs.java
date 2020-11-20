package lofitsky.iteratia.model;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

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