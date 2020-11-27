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
    query(GRAPHQL_QUERY_ALL_STATS, (response) => {
        response = response.allStats;
        if (response.length != 0) {
            stats_records = response;
        }

        if (callback) {
            callback.call(response);
        }

        stats_stopAnimation();
    });
}

function stats_createNewTR(record) {
    var html = "<tr>";
    html += "<td class=\"stats-col-char-code\">" + record.from.charCode + "</td>";
    html += "<td class=\"stats-col-num-code\">" + record.from.numCode + "</td>";
    html += "<td class=\"stats-col-name\">" + record.from.name + "</td>";
    html += "<td class=\"stats-col-char-code\">" + record.from.charCode + "</td>";
    html += "<td class=\"stats-col-char-code\">" + record.to.numCode + "</td>";
    html += "<td class=\"stats-col-name\">" + record.to.name + "</td>";
    html += "<td class=\"stats-col-sum\">" + record.totalSum + "</td>";
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