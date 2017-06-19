package ood.srp;

import ood.srp.bean.Email;
import ood.srp.util.MailUtil;

/**
 * Created by Haochen on 2017/6/17.
 * TODO:
 */
public class EmailSender {
    private String smtpHost;
    private String altSmtpHost;
    private String emailAddress;

    public EmailSender(Configuration config) {
        this.smtpHost = config.getProperty(Configuration.SMTP_SERVER);
        this.altSmtpHost = config.getProperty(Configuration.ALT_SMTP_SERVER);
        this.emailAddress = config.getProperty(Configuration.EMAIL_ADMIN);
    }

    public void sendEmail(Email email, boolean debug) {
        try {
            if (email.getDestinationAddress().length() > 0) {
                sendEmail(email, smtpHost, debug);
            }
        } catch (Exception e) {
            try {
                sendEmail(email, altSmtpHost, debug);
            } catch (Exception e2) {
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
            }
        }
    }

    private void sendEmail(Email email, String smtpHost, boolean debug) {
        System.out.println("开始发送邮件");
        MailUtil.sendEmail(email.getDestinationAddress(), emailAddress,
                email.getSubject(), email.getMessage(), smtpHost, debug);
    }
}
