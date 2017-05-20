package minijvm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class OutOfMemoryMetaspace {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OutOfMemoryMetaspace.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                        throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }
}
