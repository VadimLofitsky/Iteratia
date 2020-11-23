document.addEventListener("DOMContentLoaded", start);

var Currency = function (numCode, charCode, value, name) {
    return {
        numCode: numCode,
        charCode: charCode,
        value: value,
        name: name
    };
};

var currencies = [];

function start() {
    exchangeInit();
}