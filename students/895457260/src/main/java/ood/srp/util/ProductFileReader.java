package ood.srp.util;

import ood.srp.bean.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/6/17.
 * TODO:
 */
public class ProductFileReader {
    public static List<Product> read(File file) throws IOException { // @02C
        List<Product> products = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");

            Product product = new Product();

            product.setId(data[0]);
            product.setDesc(data[1]);

            products.add(product);

            System.out.println("产品ID = " + data[0] + "\n");
            System.out.println("产品描述 = " + data[1] + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (br != null) {
                br.close();
            }
        }

        return products;
    }
}
