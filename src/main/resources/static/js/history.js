var history_lastId = 0;
var history_newOperations = [];
var history_operationsTop = 20;

function history_init() {
    history_updateTable();
}

function history_onTabActive() {
    history_updateTable();
}

function history_updateTable() {
    history_getNewOperations();

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

function history_getNewOperations() {
    $.ajax({
        url: ROUTE_GET_TOP_OPERATIONS,
        method: "GET",
        async: false,
        cache: false,
        data: {
          lastId: history_lastId
        },
        headers: {
            Accept: "application/json; charset=utf-8"
        },
        success: (response) => {
            history_newOperations = response;
        }
    });
}

function history_createNewTR(operation) {
    var html = "<tr>";
    html += "<td class=\"history-col-onum\">" + operation.id + "</td>";
    html += "<td class=\"history-col-date\">" + operation.date + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.charCode1 + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.numCode1 + "</td>";
    html += "<td class=\"history-col-name\">" + operation.name1 + "</td>";
    html += "<td class=\"history-col-amount\">" + operation.amount1 + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.charCode2 + "</td>";
    html += "<td class=\"history-col-char-code\">" + operation.numCode2 + "</td>";
    html += "<td class=\"history-col-name\">" + operation.name2 + "</td>";
    html += "<td class=\"history-col-amount\">" + operation.amount2 + "</td>";
    html += "<td class=\"history-col-rate\">" + operation.rate + "</td>";
    html += "</tr>";
    return html;
}