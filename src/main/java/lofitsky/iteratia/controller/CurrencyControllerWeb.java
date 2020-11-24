package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(Endpoints.MAPPING_ROOT)
public class CurrencyControllerWeb {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String main(Model model) {
        model.addAttribute("form_exchange_action",
                Endpoints.MAPPING_ROOT + Endpoints.MAPPING_EXCHANGE_OPERATION_SAVE);

        return Endpoints.TEMPLATES_MAIN_PAGE;
    }
}