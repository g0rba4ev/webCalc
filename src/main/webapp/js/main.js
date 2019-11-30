function sendAjaxToServlet(pressedKey) {
    $.ajax({
        url : "/webCalc_war/doCalc",
        data : {
            key : pressedKey
        },
        success : function(responseText) {
            let arr = responseText.split("\n");
            $('#inputField').val(arr[0]);
            $('#result').val(arr[1])
        }
    });
}
