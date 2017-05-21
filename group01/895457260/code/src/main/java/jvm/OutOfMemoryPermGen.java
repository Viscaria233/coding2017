package jvm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Haochen on 2017/5/21.
 * TODO:
 */
public class OutOfMemoryPermGen {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        File file = new File("target/classes");
        URL[] urls = {file.toURI().toURL()};
        while (true) {
            ClassLoader loader = new URLClassLoader(urls);
            loader.loadClass("jvm.OutOfMemoryPermGen");
        }
    }
}
