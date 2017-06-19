package ood.srp.impl;

import ood.srp.Configuration;
import ood.srp.bean.Product;
import ood.srp.impl.PromotionMail;
import ood.srp.util.ProductFileReader;
import ood.srp.util.MailUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.*;
import java.util.List;

/**
 * PromotionMail Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 17, 2017</pre>
 */
public class PromotionMailTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testSendEMails() throws Exception {
//TODO: Test goes here...
        ClassLoader classLoader = this.getClass().getClassLoader();

        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        File productData = new File(classLoader.getResource("product_promotion.txt").getPath());
        List<Product> products = ProductFileReader.read(productData);

        //存有正确输出结果的文件
        File sendingResult = new File(classLoader.getResource("sending_result.txt").getPath());

        PromotionMail pe = new PromotionMail(new Configuration());
        boolean emailDebug = false;
        pe.sendEmails(products, emailDebug);

        String expected = read(sendingResult);
        Assert.assertEquals(expected, MailUtil.sendingHistory.toString());
    }

    private String read(File file) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream(file));
        char buf[] = new char[1024];
        StringBuilder builder = new StringBuilder();
        int len;

        while ((len = reader.read(buf)) != -1) {
            builder.append(buf, 0, len);
        }
        reader.close();
        return builder.toString();
    }

} 
