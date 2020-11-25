var stats_records = [];
var stats_operationsTop = 20;

function stats_init() {
    stats_updateTable();
}

function stats_onTabActive() {
    stats_getAllRecords(stats_updateTable);
}

function stats_updateTable() {
    if (stats_records.length === 0) { return; }
    
    var table = $("#stats_table>tbody")[0];

    var html = "";
    for (var i = 0; i < stats_records.length; i++) {
        html += stats_createNewTR(stats_records[i]);
    }
    table.innerHTML = html;
    
    stats_records = [];
}

function stats_getAllRecords(callback) {
    stats_startAnimation();
    $.ajax({
        url: ROUTE_STATS_GET_TOP,
        method: "GET",
        cache: false,
        headers: {
            Accept: "application/json; charset=utf-8"
        },
        success: (response) => {
            stats_records = response;
            if (callback) {
                callback.call(response);
            }
        }
    }).done(() => { stats_stopAnimation(); });
}

function stats_createNewTR(record) {
    var html = "<tr>";
    html += "<td class=\"stats-col-char-code\">" + exchange_getCurrencyCharCodeByNumCode(parseInt(record.codeFrom)) + "</td>";
    html += "<td class=\"stats-col-num-code\">" + record.codeFrom + "</td>";
    html += "<td class=\"stats-col-name\">" + exchange_getCurrencyNameByNumCode(parseInt(record.codeFrom)) + "</td>";
    html += "<td class=\"stats-col-char-code\">" + exchange_getCurrencyCharCodeByNumCode(parseInt(record.codeTo)) + "</td>";
    html += "<td class=\"stats-col-char-code\">" + record.codeTo + "</td>";
    html += "<td class=\"stats-col-name\">" + exchange_getCurrencyNameByNumCode(parseInt(record.codeTo)) + "</td>";
    html += "<td class=\"stats-col-sum\">" + record.sumTotal + "</td>";
    html += "<td class=\"stats-col-rate\">" + record.avgRate + "</td>";
    html += "</tr>";
    return html;
}

function stats_startAnimation() {
    startAnimation($(".stat-rotatable"));
}

function stats_stopAnimation() {
    stopAnimation($(".stat-rotatable"));
}