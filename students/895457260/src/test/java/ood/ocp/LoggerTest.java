package ood.ocp;

import ood.ocp.impl.formatter.RawLogFormatter;
import ood.ocp.impl.formatter.RawLogWithDateFormatter;
import ood.ocp.impl.sender.MailLogSender;
import ood.ocp.impl.sender.PrintLogSender;
import ood.ocp.impl.sender.SmsLogSender;
import ood.ocp.util.MailUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Logger Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 19, 2017</pre>
 */
public class LoggerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: log(String msg)
     */
    @Test
    public void testLog() throws Exception {
//TODO: Test goes here...
        Logger rawMail = new Logger(new RawLogFormatter(), new MailLogSender());
        Logger rawSms = new Logger(new RawLogFormatter(), new SmsLogSender());
        Logger rawPrint = new Logger(new RawLogFormatter(), new PrintLogSender());
        Logger dateMail = new Logger(new RawLogWithDateFormatter(), new MailLogSender());
        Logger dateSms = new Logger(new RawLogWithDateFormatter(), new SmsLogSender());
        Logger datePrint = new Logger(new RawLogWithDateFormatter(), new PrintLogSender());
        String msg = "msg";

        rawMail.log(msg);
        rawSms.log(msg);
        rawPrint.log(msg);
        dateMail.log(msg);
        dateSms.log(msg);
        datePrint.log(msg);
    }


} 
