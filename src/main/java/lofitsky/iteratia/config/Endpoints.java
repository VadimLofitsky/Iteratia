package lofitsky.iteratia.config;

/**
 * Пути эндпоинтов и HTML-страниц сервиса.
 */

public final class Endpoints {

    public static final String MAPPING_ROOT = "/currency_exchange";
    public static final String TEMPLATES_MAIN_PAGE = "index";

    public static final String MAPPING_EXCHANGE_GET_ALL_CURRENCIES = "/currency";
    public static final String MAPPING_EXCHANGE_OPERATION_SAVE = "/save-exchange-operation";

    public static final String MAPPING_HISTORY_GET_TOP = "/history/top";

    public static final String MAPPING_STATS_GET_TOP = "/stats/top";
    public static final String MAPPING_EXIT_APP = "/exit";
}