package Utilities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

    public class SMSSender {

        public static final String ACCOUNT_SID = "AC83e61e0abc912baf8dc322e7e6b71f20";
        public static final String AUTH_TOKEN = "72f4e5524969db6f75fc0ca4cef73a16";
        public static final String FROM_NUMBER = "++18773802908"; // your Twilio phone number

        public static void sendReceipt(String toNumber, String messageBody) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(
                    new PhoneNumber(toNumber),
                    new PhoneNumber(FROM_NUMBER),
                    messageBody
            ).create();

            System.out.println("📤 Receipt sent! SID: " + message.getSid());
        }
    }

