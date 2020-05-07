var webSocket;

/**
 * Connect to the web socket server on page load.
 */
$(function() {
    connect();
});

/**
 * Send a message to the server when the message box is in focus and the enter
 * key is pressed.
 */
$(document).on("keypress", function(e) {
    let responseInputInFocus = $("#response-input").is(":focus");
    let responseInputHasText = $("#response-input").val().length !== 0;
    if (responseInputInFocus && responseInputHasText && e.which == 13) {
        let messageToSend = $("#response-input").val();
        sendMessage(messageToSend);
        $("#response-input").val("");
    }
});

/**
 * Send a message to the server.
 * 
 * @param messageToSend
 *            the message to send
 */
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

/**
 * Connect to the web socket server.
 */
function connect() {
    webSocket = new WebSocket(buildWebSocketUrl());
    webSocket.onopen = webSocketOnOpen;
    webSocket.onclose = webSocketOnClose;
    webSocket.onerror = webSocketOnError;
    webSocket.onmessage = webSocketOnMessage;
}

/**
 * Build the web socket URL based on the current window location and protocol.
 * 
 * @returns the url
 */
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

/**
 * Web socket on open event handler. Sets the thread connection status to
 * connected.
 * 
 * @param event
 *            the event
 */
function webSocketOnOpen(event) {
    setThreadConnectionStatus(true);
}

/**
 * Web socket on close event handler. Sets the thread connection status to
 * disconnected and starts a reconnect process.
 * 
 * @param event
 *            the event
 */
function webSocketOnClose(event) {
    setThreadConnectionStatus(false);

    // Attempt to restart the connection
    setTimeout(function() {
        connect()
    }, 1000);
}

function webSocketOnError(event) {
    // Nothing to do here at the moment
}

/**
 * Web socket on message event handler. Removes the oldest message if the thread
 * is too long and appends the new message. Also scrolls to the bottom of the
 * thread.
 * 
 * @param event
 *            the event
 */
function webSocketOnMessage(event) {
    let message = JSON.parse(event.data);

    // Remove the first message if the message thread is too long
    if ($(".message-thread").children().length >= 50) {
        $(".message-thread").find(".message").first().remove();
    }

    // Append the new message
    let $messageDiv = $(document.createElement("div"));
    $messageDiv.text(message.message);
    $messageDiv.addClass("message");

    // Add an extra class if this is a system message
    if (message.messageType === "SYSTEM") {
        $messageDiv.addClass("system-message");
    }
    $(".message-thread").append($messageDiv);

    // Scroll to the bottom of the thread
    $(".message-thread").animate({
        scrollTop : $(".message-thread")[0].scrollHeight
    }, 200);
}

/**
 * Set the thread connection status.
 * 
 * @param connected
 *            boolean indicating if we're connected or disconnected
 */
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