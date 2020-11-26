package lofitsky.iteratia.service;

import lofitsky.iteratia.auxiliary.ValCurs;
import lofitsky.iteratia.model.Currency;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Сервис по получению данных о курсах валют.<br>
 * Источник данных - сервис ЦБ РФ,
 * <a href="https://www.cbr.ru/scripts/XML_daily.asp">https://www.cbr.ru/scripts/XML_daily.asp</a>
 */

@Service
public class CbrService {

    private final String CBR_XML_CURRENCIES_DAILY_URL = "https://www.cbr.ru/scripts/XML_daily.asp";

    private Unmarshaller UNMARSHALLER;

    private ValCurs valcurs = null;

    public CbrService() {
        try {
            UNMARSHALLER = JAXBContext.newInstance(ValCurs.class).createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает курсы валют виде XML и преобразует в объект {@link ValCurs}.<br>
     * Источник данных указывается в <i><b>Endpoints.CBR_XML_CURRENCIES_DAILY_URL</b></i>.
     * @see ValCurs
     */
    private void parse() {
        try {
            valcurs = (ValCurs) UNMARSHALLER.unmarshal(new URL(CBR_XML_CURRENCIES_DAILY_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает список валют (объектов {@link Currency}).
     * @return список валют
     * @see Currency
     */
    public List<Currency> getCurrencies() {
        parse();
        return valcurs.getValutes();
    }

    /**
     * Возвращает дату, на которую получены курсы.
     * Соответствует атрибуту Date корневого элемента ValCurs
     * @return java.util.Date дату
     */
    public Date getDate() {
        try {
            SimpleDateFormat fromXML = new SimpleDateFormat("dd.MM.yyyy");
            return fromXML.parse(valcurs.getDate());
        } catch (ParseException e) {
            return null;
        }
    }
}