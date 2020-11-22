package lofitsky.iteratia.auxiliary;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XMLStringFloatAdapter extends XmlAdapter<String, Float> {
    @Override
    public Float unmarshal(String s) throws Exception {
        return Float.parseFloat(s.replace(",", "."));
    }

    @Override
    public String marshal(Float aFloat) throws Exception {
        return aFloat.toString();
    }
}