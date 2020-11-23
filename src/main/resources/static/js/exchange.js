var floatNumRe = /(?:\d*\.?\d+|\d+\.?\d*)/;

var exchange_form,
    currency1_name, currency2_name,
    exchange_selectFrom, exchange_selectTo,
    exchange_inputAmount1, exchange_inputAmount2;

function exchangeInit() {
    exchange_form = $("form#form_exchange")[0];
    $(exchange_form).submit(formExchangeSubmit);

    currency1_name = $("#currency1-name")[0];
    currency2_name = $("#currency2-name")[0];

    exchange_selectFrom = exchange_form.elements.curr1;
    exchange_selectTo = exchange_form.elements.curr2;

    exchange_inputAmount1 = exchange_form.elements.amount1;
    exchange_inputAmount2 = exchange_form.elements.amount2;

    $(exchange_selectFrom).change(selectFromChange);
    $(exchange_selectTo).change(selectToChange);
    $(exchange_inputAmount1).keyup(inputFromChange);

    getCurrencies();
    fillSelects();
}

function getCurrencies() {
    $(exchange_selectFrom.options).each((i, opt) => {
        currencies.push(new Currency(opt.value, opt.innerText, opt.dataset.rate, opt.dataset.name));
    });
}

function fillSelects() {
    fillSelectFrom();

    exchange_selectTo.innerHTML = exchange_selectFrom.innerHTML;

    exchange_selectFrom.selectedIndex = 0;
    selectFromChange();
    selectToChange();
}

function fillSelectFrom() {
}

function selectFromChange() {
    var selectedFrom = exchange_selectFrom.selectedOptions.item(0);
    var optionsTo = exchange_selectTo.options;

    currency1_name.innerText = selectedFrom.dataset.name;

    $(optionsTo).filter((i, opt) => { return opt.classList.contains("not-available"); })
        .removeClass("not-available");

    $(optionsTo)
        .filter((i, opt) => { return opt.innerText === selectedFrom.innerText; })
        .addClass("not-available");

    exchange_selectTo.selectedIndex = (exchange_selectFrom.selectedIndex != 0) ? 0 : 1;
    selectToChange();

    inputFromChange();
}

function selectToChange() {
    var selectedTo = exchange_selectTo.selectedOptions.item(0);
    currency2_name.innerText = selectedTo.dataset.name;

    inputFromChange();
}

function formExchangeSubmit() {
    let dt = new Date();
    let date = Math.floor(new Date(dt.getUTCFullYear(),
                                         dt.getUTCMonth(),
                                         dt.getUTCDate(),
                                         dt.getUTCHours(),
                                         dt.getUTCMinutes(),
                                         dt.getUTCSeconds()).getTime() / 1000);

    date -= dt.getTimezoneOffset() * 60;     // ???

    $.ajax({
        url: exchange_form.action,
        method: "POST",
        // type: "POST",
        cache: false,
        data: {
            curr1: exchange_form.elements.curr1.value,
            curr2: exchange_form.elements.curr2.value,
            amount1: exchange_form.elements.amount1.value,
            date: date
        }
    }).done((response) => {
        exchange_reset();
    });

    return false;
}

function exchange_RelRate() {
    var opt1 = exchange_form.elements.curr1.selectedOptions[0];
    var opt2 = exchange_form.elements.curr2.selectedOptions[0];

    return  opt1.dataset.rate * opt2.dataset.nom / (opt2.dataset.rate * opt1.dataset.nom);
}

function inputFromChange() {
    var s = exchange_inputAmount1.value;
    exchange_inputAmount2.value = (floatNumRe.test(s)) ? exchange_RelRate() * parseFloat(s) : "0";
}

function exchange_reset() {
    exchange_selectFrom.selectedIndex = 0;
    exchange_inputAmount1.value = "0";
    selectFromChange();
    inputFromChange();
    selectToChange();
}