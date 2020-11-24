package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CurrencyControllerWeb {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(Endpoints.MAPPING_ROOT)
    public String main(Model model) {
        model.addAttribute("form_exchange_action", Endpoints.MAPPING_EXCHANGE_OPERATION_SAVE);

        return Endpoints.TEMPLATES_MAIN_PAGE;
    }
}