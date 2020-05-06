var webSocket;

$(function() {
    connect();
});

$(document).on("keypress", function(e) {
    let responseInputInFocus = $("#response-input").is(":focus");
    if (responseInputInFocus && e.which == 13) {
        let messageToSend = $("#response-input").val();
        sendMessage(messageToSend);
        $("#response-input").val("");
    }
});

function sendMessage(messageToSend) {
    $.ajax({
        url : "/web-socket/submit-message",
        type : "POST",
        data : JSON.stringify({
            message : messageToSend
        }),
        contentType : "application/json",
        dataType : "json"
    });
}

function connect() {
    webSocket = new WebSocket(buildWebSocketUrl());
    webSocket.onopen = webSocketOnOpen;
    webSocket.onclose = webSocketOnClose;
    webSocket.onerror = webSocketOnError;
    webSocket.onmessage = webSocketOnMessage;
}

function buildWebSocketUrl() {
    let url = "";
    if (window.location.protocol === "https:") {
        url += "wss://";
    } else {
        url += "ws://";
    }
    url += window.location.host;
    url += window.location.pathname + "web-socket";
    return url;
}

function webSocketOnOpen(event) {
    console.log("Web socket opened.", event)
    setThreadConnectionStatus(true);
}

function webSocketOnClose(event) {
    console.log("Web socket closed.", event)
    setThreadConnectionStatus(false);
}

function webSocketOnError(event) {
    console.log("Web socket error occurred.", event)
}

function webSocketOnMessage(event) {
    let message = JSON.parse(event.data);

    // Remove the first message if the message thread is too long
    if ($(".message-thread").children().length >= 50) {
        $(".message-thread").find(".message").first().remove();
    }

    // Append the new message
    $(".message-thread").append("<div class=\"message\">" + message.message + "</div>");

    // Scroll to the bottom of the thread
    $(".message-thread").animate({
        scrollTop : $(".message-thread")[0].scrollHeight
    }, 200);
}

function setThreadConnectionStatus(connected) {
    let $threadConnectionStatus = $(".thread-status-connection-status");
    if (connected) {
        $threadConnectionStatus.text("CONNECTED");
        $threadConnectionStatus.addClass("connection-status-connected").removeClass("connection-status-disconnected");
    } else {
        $threadConnectionStatus.text("DISCONNECTED");
        $threadConnectionStatus.addClass("connection-status-disconnected").removeClass("connection-status-connected");
    }
}