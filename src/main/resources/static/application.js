var webSocket;

$(function() {
    connect();
});

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
}

function webSocketOnClose(event) {
    console.log("Web socket closed.", event)
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