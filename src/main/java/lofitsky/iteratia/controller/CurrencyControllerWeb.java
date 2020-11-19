package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Endpoints.MAPPING_ROOT)
public class CurrencyControllerWeb {

    @GetMapping
    public String main() {
        return Endpoints.TEMPLATES_MAIN_PAGE;
    }
}