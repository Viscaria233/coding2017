package ood.ocp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getCurrentDateAsString() {
        return SimpleDateFormat.getDateTimeInstance().format(new Date());
    }

}
