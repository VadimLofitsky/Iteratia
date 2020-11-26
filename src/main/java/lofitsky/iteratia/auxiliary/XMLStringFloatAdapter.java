package lofitsky.iteratia.auxiliary;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Адаптер, используемый при отображении XML ответа сервиса ЦБ по курсам валют
 * для преобразования строки в число типа <i>float</i>.
 */

public class XMLStringFloatAdapter extends XmlAdapter<String, Float> {
    @Override
    public Float unmarshal(String s) {
        // замена знака разделения частей дробного числа
        return Float.parseFloat(s.replace(",", "."));
    }

    @Override
    public String marshal(Float aFloat) {
        return aFloat.toString();
    }
}