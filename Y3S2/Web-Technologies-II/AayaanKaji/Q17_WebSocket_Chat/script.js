let chatSocket = null;

function connectToWebSocket() {
  const protocol = window.location.protocol === "https:" ? "wss:" : "ws:";
  const host = window.location.hostname;
  const port = window.location.port ? ":" + window.location.port : "";
  const socketUrl = protocol + "//" + host + port + "/AayaanKaji/Q17_WebSocket_Chat/chat";

  chatSocket = new WebSocket(socketUrl);

  chatSocket.onopen = () => {
    consoleLog("Connected to WebSocket server.");
    document.getElementById("chat").disabled = false;
  };

  chatSocket.onclose = () => {
    consoleLog("Disconnected from server.");
    document.getElementById("chat").disabled = true;
  };

  chatSocket.onerror = (error) => {
    consoleLog("WebSocket error.");
    console.error(error);
  };

  chatSocket.onmessage = (event) => {
    const msg = event.data;
    if (msg.startsWith("__userlist__:")) {
      const users = msg.substring("__userlist__:".length).split(",");
      updateUserList(users);
    } else {
      consoleLog(msg);
    }
  };
}

function consoleLog(msg) {
  const log = document.getElementById("console");
  const p = document.createElement("p");
  p.textContent = msg;
  log.appendChild(p);
  log.scrollTop = log.scrollHeight;
}

function sendMessage() {
  const input = document.getElementById("chat");
  const msg = input.value.trim();
  if (msg && chatSocket.readyState === WebSocket.OPEN) {
    chatSocket.send(msg);
    input.value = "";
  }
}

function updateUserList(users) {
  const list = document.getElementById("userlist");
  list.innerHTML = "";
  users.forEach((user) => {
    const li = document.createElement("li");
    li.textContent = user;
    list.appendChild(li);
  });
}

document.addEventListener("DOMContentLoaded", () => {
  connectToWebSocket();
  document.getElementById("chat").addEventListener("keydown", (e) => {
    if (e.key === "Enter") sendMessage();
  });
});
