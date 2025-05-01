package Q17_WebSocket_Chat;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import utils.*;

@ServerEndpoint("/Q17_WebSocket_Chat/chat")
public class ChatEndpoint {

    private static final Logger log = Logger.getLogger(ChatEndpoint.class.getName());
    private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatEndpoint> connections = new CopyOnWriteArraySet<>();

    private final String nickname;
    private Session session;
    private final Queue<String> messageBacklog = new ConcurrentLinkedQueue<>();
    private boolean messageInProgress = false;

    public ChatEndpoint() {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    @OnOpen
    public void start(Session session) {
        this.session = session;
        connections.add(this);
        broadcast(String.format("+ %s has joined.", nickname));
    }

    @OnClose
    public void end() {
        connections.remove(this);
        broadcast(String.format("- %s has disconnected.", nickname));
    }

    @OnMessage
    public void incoming(String message) {
        if (message == null)
            return;

        // Sanitize input
        String filteredMessage = String.format("%s: %s", nickname, HtmlUtil.escapeHtml(message));
        broadcast(filteredMessage);
    }

    @OnError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, "Chat Error: " + t.toString(), t);
    }

    private void sendMessage(String msg) throws IOException {
        synchronized (this) {
            if (messageInProgress) {
                messageBacklog.add(msg);
                return;
            }
            messageInProgress = true;
        }

        try {
            String messageToSend = msg;
            while (messageToSend != null) {
                session.getBasicRemote().sendText(messageToSend);
                synchronized (this) {
                    messageToSend = messageBacklog.poll();
                    if (messageToSend == null) {
                        messageInProgress = false;
                    }
                }
            }
        } catch (IOException e) {
            messageInProgress = false;
            throw e;
        }
    }

    private static void broadcast(String msg) {
        Iterator<ChatEndpoint> iterator = connections.iterator();
        while (iterator.hasNext()) {
            ChatEndpoint client = iterator.next();
            try {
                client.sendMessage(msg);
            } catch (IOException e) {
                log.log(Level.WARNING, "Chat Error: Failed to send message to client", e);
                try {
                    client.session.close();
                } catch (IOException ignore) {
                }

                iterator.remove(); // avoid recursive broadcast
                broadcast(String.format("- %s has been disconnected.", client.nickname));
            }
        }

        broadcastUserList(); // send the updated user list once after the message is broadcasted
    }

    private static void broadcastUserList() {
        StringBuilder userList = new StringBuilder("__userlist__:");
        for (ChatEndpoint client : connections) {
            userList.append(client.nickname).append(",");
        }
        if (userList.charAt(userList.length() - 1) == ',') {
            userList.setLength(userList.length() - 1); // trim trailing comma
        }
        for (ChatEndpoint client : connections) {
            try {
                client.sendMessage(userList.toString());
            } catch (IOException e) {
                log.log(Level.WARNING, "Failed to send user list", e);
            }
        }
    }
}
