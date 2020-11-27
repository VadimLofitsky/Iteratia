var floatNumRe = /(?:\d*\.?\d+|\d+\.?\d*)/;

var Currency = function (numCode, charCode, value, nominal, name) {
    return {
        numCode: numCode,
        charCode: charCode,
        value: value,
        nominal: nominal,
        name: name
    };
};

var exchange_currencies = [];
var exchange_form,
    currency1_name, currency2_name,
    exchange_selectFrom, exchange_selectTo,
    exchange_inputAmount1, exchange_inputAmount2;

function exchange_init() {
    exchange_form = $("form#form_exchange")[0];
    $(exchange_form).submit(exchange_formExchangeSubmit);

    currency1_name = $("#currency1-name")[0];
    currency2_name = $("#currency2-name")[0];

    exchange_selectFrom = exchange_form.elements.curr1;
    exchange_selectTo = exchange_form.elements.curr2;

    exchange_inputAmount1 = exchange_form.elements.amount1;
    exchange_inputAmount2 = exchange_form.elements.amount2;

    $(exchange_selectFrom).change(exchange_onSelectFromChange);
    $(exchange_selectTo).change(exchange_onSelectToChange);
    $(exchange_inputAmount1).keyup(exchange_inputFromChange);

    exchange_checkForUpdates(true, exchange_fillSelects);
}

function exchange_onTabActive() {
    exchange_checkForUpdates(false, exchange_fillSelects);
}

function exchange_checkForUpdates(force = false, callback) {
    exchange_getCurrencies(force, callback);
}

function exchange_getCurrencies(force, callback) {
    exchange_startAnimation();
    query(GRAPHQL_QUERY_ALL_CURRENCIES, (response) => {
            response = response.allCurrencies;
            if (response.length != 0) {
                exchange_currencies = [];
                response.forEach((curr) => {
                    exchange_currencies.push(new Currency(curr.numCode,
                                                          curr.charCode,
                                                          curr.value,
                                                          curr.nominal,
                                                          curr.name));
                });
            }

            if (callback) {
                callback.call(response);
            }

            exchange_stopAnimation();
        });
}

function exchange_fillSelects() {
    exchange_fillSelectFrom();

    exchange_selectTo.innerHTML = exchange_selectFrom.innerHTML;

    exchange_selectFrom.selectedIndex = 0;
    exchange_onSelectFromChange();
    exchange_onSelectToChange();
}

function exchange_fillSelectFrom() {
    html = exchange_currencies.map((curr) => {
            return "<option value='" + curr.numCode +
                         "' data-rate='" + curr.value +
                         "' data-nom='" + curr.nominal +
                         "' data-name='" + curr.name + "'>" + curr.charCode + "</option>";
        }).join("");

    exchange_selectFrom.innerHTML = html;
}

function exchange_onSelectFromChange() {
    var selectedFrom = exchange_selectFrom.selectedOptions.item(0);
    var optionsTo = exchange_selectTo.options;

    currency1_name.innerText = selectedFrom.dataset.name;

    $(optionsTo).filter((i, opt) => { return opt.classList.contains("not-available"); })
        .removeClass("not-available");

    $(optionsTo)
        .filter((i, opt) => { return opt.innerText === selectedFrom.innerText; })
        .addClass("not-available");

    exchange_selectTo.selectedIndex = (exchange_selectFrom.selectedIndex != 0) ? 0 : 1;
    exchange_onSelectToChange();

    exchange_inputFromChange();
}

function exchange_onSelectToChange() {
    var selectedTo = exchange_selectTo.selectedOptions.item(0);
    currency2_name.innerText = selectedTo.dataset.name;

    exchange_inputFromChange();
}

function exchange_formExchangeSubmit() {
    let date = dateToUTC(new Date());
    let q = query_prepare(GRAPHQL_MUTATION_CREATE_EXCHANGE_HISTORYO_PERATION, [
        date,
        exchange_form.elements.curr1.value,
        exchange_form.elements.curr2.value,
        exchange_form.elements.amount1.value
    ]);

    query(q, () => {
        exchange_reset();
    });

    return false;
}

function exchange_relRate() {
    var opt1 = exchange_form.elements.curr1.selectedOptions[0];
    var opt2 = exchange_form.elements.curr2.selectedOptions[0];

    return  opt1.dataset.rate * opt2.dataset.nom / (opt2.dataset.rate * opt1.dataset.nom);
}

function exchange_inputFromChange() {
    var s = exchange_inputAmount1.value;
    exchange_inputAmount2.value = (floatNumRe.test(s)) ? exchange_relRate() * parseFloat(s) : "0";
}

function exchange_reset() {
    exchange_selectFrom.selectedIndex = 0;
    exchange_inputAmount1.value = "0";
    exchange_onSelectFromChange();
    exchange_inputFromChange();
    exchange_onSelectToChange();
}

function exchange_getCurrencyNameByNumCode(numCode) {
    return exchange_currencies
        .filter((v) => { return v.numCode == numCode; })[0]
        .name;
}

function exchange_getCurrencyCharCodeByNumCode(numCode) {
    return exchange_currencies
        .filter((v) => { return v.numCode == numCode; })[0]
        .charCode;
}

function exchange_startAnimation() {
    startAnimation($(".exchange-rotatable"));
}

function exchange_stopAnimation() {
    stopAnimation($(".exchange-rotatable"));
}