package ood.ocp.util;

public class SMSUtil {

    public static final String PRE = "SMS sent: ";

    public static void send(String logMsg) {
        System.out.println(PRE + logMsg);
    }

}
