package src;

public class User {
    private String name;

    User (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage (ChatRoom chatRoom, String message) {
        chatRoom.showMessage(this, message);
    }
}
