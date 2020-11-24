package lofitsky.iteratia.auxiliary;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XMLStringFloatAdapter extends XmlAdapter<String, Float> {
    @Override
    public Float unmarshal(String s) {
        return Float.parseFloat(s.replace(",", "."));
    }

    @Override
    public String marshal(Float aFloat) {
        return aFloat.toString();
    }
}