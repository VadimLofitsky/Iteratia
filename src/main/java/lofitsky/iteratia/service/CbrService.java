package lofitsky.iteratia.service;

import lofitsky.iteratia.config.Endpoints;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.model.ValCurs;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CbrService {

    private Unmarshaller UNMARSHALLER;

    private boolean cached = false;
    private ValCurs valcurs = null;

    public CbrService() {
        try {
            UNMARSHALLER = JAXBContext.newInstance(ValCurs.class).createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private List<Currency> getValutes(boolean useCache) {
        if (cached && useCache) {
            return valcurs.getValutes();
        } else {
            return getValutes();
        }
    }

    private List<Currency> getValutes() {
        parse();
        return valcurs.getValutes();
    }

    private void parse() {
        try {
            valcurs = (ValCurs) UNMARSHALLER.unmarshal(new URL(Endpoints.CBR_XML_CURRENCIES_DAILY_URL));
            cached = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Currency> getCurrencies(boolean cached) {
        return getValutes(cached);
    }

    public Date getDate() {
        try {
            SimpleDateFormat fromXML = new SimpleDateFormat("dd.MM.yyyy");
            return fromXML.parse(valcurs.getDate());
        } catch (ParseException e) {
            return null;
        }
    }
}