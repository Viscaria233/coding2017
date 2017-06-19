package ood.srp.util;

public class MailUtil {

    public static void sendEmail(String toAddress, String fromAddress, String subject,
                                 String message, String smtpHost, boolean debug) {
        //假装发了一封邮件
        StringBuilder builder = new StringBuilder();
        builder.append("From:").append(fromAddress).append("\n");
        builder.append("To:").append(toAddress).append("\n");
        builder.append("Subject:").append(subject).append("\n");
        builder.append("Content:").append(message).append("\n");

        System.out.println(builder.toString());
    }

}
