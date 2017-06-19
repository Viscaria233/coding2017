package ood.ocp.impl.sender;

import ood.ocp.LogSender;

/**
 * Created by Haochen on 2017/6/19.
 * TODO:
 */
public class PrintLogSender implements LogSender {
    @Override
    public void send(String message) {
        System.out.println(message);
    }
}
