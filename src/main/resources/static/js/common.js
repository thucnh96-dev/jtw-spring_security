var API = {

    log : function (msg) {
        console.log(msg);
    },

    alert : function (msg) {
        alert(msg);
    },

    ajaxPost: function(url, headers, params, callback) {
        API.log("API.ajaxPost");
        API.log(headers);
        API.log(params);
        $.ajax({
            url: url,
            type: 'post',
            headers: headers,
            data: JSON.stringify(params),
            contentType: "application/json; charset=UTF-8",
            dataType: 'json',
            cache: false,
            success: function (data) {
                API.log(data);
                var result = data.status;
                var message = data.message;
                var object = data.object;
                if (result) {
                    callback(message, object);
                } else {
                    API.alert(message, true);
                }
            },
            error: function (request,status,error) {
                API.alert("code:"+request.status+"<br>"+"message:"+request.responseText+"<br>"+"error:"+error, true);
            }
        });
    },
    ajaxPostFormData: function(url, headers, formData, callback) {
        $.ajax({
            url: url,
            type: 'post',
            headers: headers,
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                API.log(data);
                var result = data.status;
                var message = data.message;
                var object = data.object;
                if (result) {
                    callback(message, object);
                } else {
                    API.alert(message, true);
                }
            },
            error: function (e) {
                API.alert(e.message, true);
            }
        });
    },
    logCallback : function(msg, object) {
        var jsonObj = JSON.parse(object);
        Wizard.log("logCallback : jsonObj is " + jsonObj);
    }
}