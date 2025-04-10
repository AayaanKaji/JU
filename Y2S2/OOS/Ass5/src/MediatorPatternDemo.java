package src;

public class MediatorPatternDemo {
    public static void main(String[] args) throws InterruptedException {
        User u1 = new User("John Hammond");
        User u2 = new User("Dr. Ian Malcolm");

        ChatRoom cr1 = new ChatRoom();

        u1.sendMessage(cr1, "You're implying that a group of... wild animals, uh, somehow escaped.");
        Thread.sleep(10000);
        u2.sendMessage(cr1, "No, John. I'm simply saying that life... finds a way.");
    }
}
