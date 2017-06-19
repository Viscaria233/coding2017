package ood.ocp.impl.sender;

import ood.ocp.LogSender;
import ood.ocp.util.SMSUtil;

/**
 * Created by Haochen on 2017/6/19.
 * TODO:
 */
public class SmsLogSender implements LogSender {
    @Override
    public void send(String message) {
        SMSUtil.send(message);
    }
}
