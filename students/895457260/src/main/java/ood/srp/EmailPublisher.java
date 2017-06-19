package ood.srp;

import ood.srp.bean.Email;
import ood.srp.bean.Product;
import ood.srp.bean.User;

import java.util.List;

/**
 * Created by Haochen on 2017/6/18.
 * TODO:
 */
public abstract class EmailPublisher {
    private EmailSender sender;

    public EmailPublisher(Configuration config) throws Exception {
        sender = new EmailSender(config);
    }

    public void sendEmails(List<Product> products, boolean debug) throws Exception {
        for (Product product : products) {
            List<User> mailingList = getMailingList(product);
            if (mailingList != null) {
                sendEmails(mailingList, product, debug);
            } else {
                System.out.println("没有邮件发送");
            }
        }
    }

    private void sendEmails(List<User> mailingList, Product product, boolean debug) {
        for (User user : mailingList) {
            Email email = configureEmail(user, product);
            if (email != null) {
                sender.sendEmail(email, debug);
            }
        }
    }

    protected abstract Email configureEmail(User user, Product product);
    protected abstract List<User> getMailingList(Product product) throws Exception;
}
