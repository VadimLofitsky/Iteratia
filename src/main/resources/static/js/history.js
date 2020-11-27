var history_lastId = 0;
var history_newOperations = [];
var history_operationsTop = 20;

function history_init() {
    history_updateTable();
}

function history_onTabActive() {
    history_getNewOperations(history_updateTable);
}

function history_updateTable() {
    if (history_newOperations.length == 0) { return; }
    
    var table = $("#history_table>tbody")[0];

    var html = "";
    for (var i = 0; i < history_newOperations.length; i++) {
        html += history_createNewTR(history_newOperations[i]);
    }
    table.innerHTML = html + table.innerHTML;
    
    history_lastId = parseInt(history_newOperations[0].id);
    history_newOperations = [];
}

function history_getNewOperations(callback) {
    history_startAnimation();
    let q = query_prepare(GRAPHQL_QUERY_HISTORY_RECORDS, [history_lastId]);
    query(q, (response) => {
        response = response.historyRecords;
        if (response.length != 0) {
            history_newOperations = response;
        }

        if (callback) {
            callback.call(response);
        }

        history_stopAnimation();
    });
}

function history_createNewTR(operation) {
    var html = "<tr>";
    html += "<td class=\"history-col-onum\">" + operation.id + "</td>";
    html += "<td class=\"history-col-date\">" + operation.date + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.from.charCode + "</td>";
    html += "<td class=\"history-col-num-code\">" + operation.from.numCode + "</td>";
    html += "<td class=\"history-col-name\">" + operation.from.name + "</td>";
    html += "<td class=\"history-col-amount\">" + operation.amountFrom + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.to.charCode + "</td>";
    html += "<td class=\"history-col-num-code\">" + operation.to.numCode + "</td>";
    html += "<td class=\"history-col-name\">" + operation.to.name + "</td>";
    html += "<td class=\"history-col-amount\">" + operation.amountTo + "</td>";
    html += "<td class=\"history-col-rate\">" + operation.rate + "</td>";
    html += "</tr>";
    return html;
}

function history_startAnimation() {
    startAnimation($(".history-rotatable"));
}

function history_stopAnimation() {
    stopAnimation($(".history-rotatable"));
}