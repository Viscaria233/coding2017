package ood.ocp.impl.formatter;

import ood.ocp.util.DateUtil;
import ood.ocp.LogFormatter;

/**
 * Created by Haochen on 2017/6/19.
 * TODO:
 */
public class RawLogWithDateFormatter implements LogFormatter {
    @Override
    public String format(String originalMsg) {
        return DateUtil.getCurrentDateAsString() + ": " + originalMsg;
    }
}
