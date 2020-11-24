document.addEventListener("DOMContentLoaded", start);

function start() {
    setupOnTabActiveHandlers();

    exchange_checkForUpdates(true, false);

    exchange_init();
    history_init();
    stats_init();
}

function setupOnTabActiveHandlers() {
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var tab = e.target.attributes.href.value.substr(1);
        switch (tab) {
            case "exchange": {
                exchange_onTabActive();
                break;
            }
            case "history": {
                history_onTabActive();
                break;
            }
            case "stat": {
                stats_onTabActive();
                break;
            }
        }
    });
}

function dateToUTC(dt) {
    let date = Math.floor(new Date(dt.getUTCFullYear(),
        dt.getUTCMonth(),
        dt.getUTCDate(),
        dt.getUTCHours(),
        dt.getUTCMinutes(),
        dt.getUTCSeconds()).getTime() / 1000);

    date -= dt.getTimezoneOffset() * 60;     // ???
    return date;
}