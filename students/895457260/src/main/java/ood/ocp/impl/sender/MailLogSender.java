package ood.ocp.impl.sender;

import ood.ocp.LogSender;
import ood.ocp.util.MailUtil;

/**
 * Created by Haochen on 2017/6/19.
 * TODO:
 */
public class MailLogSender implements LogSender {
    @Override
    public void send(String message) {
        MailUtil.send(message);
    }
}
