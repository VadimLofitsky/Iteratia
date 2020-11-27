// функция для выполнения запроса к GraphQL
function query(query, callback) {
    $.ajax({
        url: ROUTE_GRAPHQL,
        method: "POST",
        cache: false,
        headers: { Accept: "application/json; charset=utf-8" },
        data: query,
        complete: (response) => {
            var data = JSON.parse(response.responseText).data;
            if (callback) {
                callback.bind(null, data).call();
            }
        }
    });
}

// функция подготовки строки запроса
// заменяет знаки вопроса в строке запроса query на аргументы массива args
function query_prepare(query, args) {
    let s = query;
    let result = "";
    let i = 0;
    let subs = s.split("?");

    for (i = 0; i < args.length; i++) {
        result += subs[i] + args[i];
    }

    result += subs[i];
    return result;
}