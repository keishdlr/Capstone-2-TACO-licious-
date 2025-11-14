package Utilities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    private static final String FROM_NUMBER = System.getenv("TWILIO_FROM_NUMBER");

    public static void sendReceipt(String toNumber, String messageBody) {
        System.out.println("🔍 API Key: " + System.getenv("TWILIO_API_KEY"));
        System.out.println("🔍 Secret: " + System.getenv("TWILIO_API_SECRET"));
        System.out.println("🔍 From #: " + System.getenv("TWILIO_FROM_NUMBER"));
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(FROM_NUMBER),
                messageBody
        ).create();

        System.out.println("📤 Receipt sent! SID: " + message.getSid());
    }
}