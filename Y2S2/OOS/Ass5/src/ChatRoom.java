package src;

import java.util.Date;
import java.util.Calendar;

public class ChatRoom {
    public void showMessage(User user, String message) {
        Date currDate = (Calendar.getInstance()).getTime();

        System.out.println(currDate + ",\n  " + user.getName() + " : " + 
                            message);
    }
}
