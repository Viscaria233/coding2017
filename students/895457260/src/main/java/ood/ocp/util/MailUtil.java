package ood.ocp.util;

public class MailUtil {

    public static final String PRE = "Mail sent: ";

	public static void send(String logMsg) {
        System.out.println(PRE + logMsg);
    }

}
