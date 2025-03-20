const socket = new WebSocket("ws://localhost:8080/chat"); // WebSocket endpoint

socket.onopen = function () {
    console.log("Connected to WebSocket server.");
};

socket.onmessage = function (event) {
    const chatBox = document.getElementById("chat-box");
    const message = document.createElement("p");
    message.textContent = event.data;
    chatBox.appendChild(message);
};

socket.onclose = function () {
    console.log("Disconnected from WebSocket server.");
};

document.getElementById("send-button").addEventListener("click", function () {
    const messageInput = document.getElementById("message-input");
    const message = messageInput.value;

    if (message.trim() !== "") {
        socket.send(message); // Send message to WebSocket server
        messageInput.value = "";
    }
});
