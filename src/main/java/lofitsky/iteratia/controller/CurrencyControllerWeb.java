package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CurrencyControllerWeb {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyControllerWeb(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(Endpoints.MAPPING_ROOT)
    public String main(Model model) {
        List<Currency> currencies = currencyService.findAll();

        model.addAttribute("form_exchange_action", Endpoints.MAPPING_EXCHANGE_OPERATION_SAVE);
        model.addAttribute("currencies", currencies);

        return Endpoints.TEMPLATES_MAIN_PAGE;
    }
}