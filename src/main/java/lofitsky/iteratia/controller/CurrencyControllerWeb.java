package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Web-контроллер для предоставления главной страницы приложения и обработки запроса на выход из него.
 */

@Controller
@RequestMapping(Endpoints.MAPPING_ROOT)
public class CurrencyControllerWeb {

    // предоставление веб-страницы приложения
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String main(Model model) {
        return Endpoints.TEMPLATES_MAIN_PAGE;
    }

    // выход из приложения по запросу веб страницы
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(Endpoints.MAPPING_EXIT_APP)
    public void exit() {
        System.out.println("Exit by user");
        System.exit(0);
    }
}