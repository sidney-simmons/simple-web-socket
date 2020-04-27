var webSocket;

$(function() {
    connect();
});

function connect() {
    webSocket = new WebSocket('ws://localhost:8080/web-socket');
    webSocket.onopen = webSocketOnOpen;
    webSocket.onclose = webSocketOnClose;
    webSocket.onerror = webSocketOnError;
    webSocket.onmessage = webSocketOnMessage;
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
    console.log("Web socket message received.", event)
}