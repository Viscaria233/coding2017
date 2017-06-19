package ood.ocp.impl.formatter;

import ood.ocp.LogFormatter;

/**
 * Created by Haochen on 2017/6/19.
 * TODO:
 */
public class RawLogFormatter implements LogFormatter {
    @Override
    public String format(String originalMsg) {
        return originalMsg;
    }
}
