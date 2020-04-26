var webSocket;

$(function() {
    connect();
});

function connect() {
    websocket = new WebSocket('ws://localhost:8080/web-socket');
}