package ood.srp.util;

import ood.srp.bean.Product;
import ood.srp.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    /**
     * 应该从数据库读， 但是简化为直接生成。
     *
     * @param sql
     * @return
     */
    public static List<User> query(String sql) {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setEmail("aa@bb.com");
            users.add(user);
        }
        return users;
    }

    public static List<User> getSubscribers(Product product) {
        String sql = "Select name from subscriptions "
                + "where product_id= '" + product.getId() + "' "
                + "and send_mail=1 ";
        return query(sql);
    }
}
