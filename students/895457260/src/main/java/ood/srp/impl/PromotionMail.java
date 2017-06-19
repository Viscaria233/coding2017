package ood.srp.impl;

import ood.srp.Configuration;
import ood.srp.EmailPublisher;
import ood.srp.bean.Email;
import ood.srp.bean.Product;
import ood.srp.bean.User;
import ood.srp.util.DBUtil;

import java.util.List;

public class PromotionMail extends EmailPublisher {

    public PromotionMail(Configuration config) throws Exception {
        super(config);
    }

    @Override
    protected Email configureEmail(User user, Product product) {
        String to = user.getEmail();

        if (to.length() <= 0) {
            return null;
        }

        Email email = new Email();
        email.setDestinationAddress(to);
        email.setSubject("您关注的产品降价了");
        email.setMessage("尊敬的 " + user.getName() + ", 您关注的产品 "
                + product.getDesc() + " 降价了，欢迎购买!");
        return email;
    }

    @Override
    protected List<User> getMailingList(Product product) throws Exception {
        return DBUtil.getSubscribers(product);
    }

}
