package lofitsky.iteratia.config;

public final class Endpoints {
    public static final String CBR_XML_CURRENCIES_DAILY_URL = "https://www.cbr.ru/scripts/XML_daily.asp";

    public static final String MAPPING_ROOT = "/currency_exchange";
    public static final String TEMPLATES_MAIN_PAGE = "index";

    public static final String MAPPING_EXCHANGE_GET_ALL_CURRENCIES = MAPPING_ROOT + "/currency";
    public static final String MAPPING_EXCHANGE_OPERATION_SAVE = MAPPING_ROOT + "/save-exchange-operation";

    public static final String MAPPING_HISTORY_GET_TOP = MAPPING_ROOT + "/get-top";
}