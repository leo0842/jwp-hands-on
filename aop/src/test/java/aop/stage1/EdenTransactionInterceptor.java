package aop.stage1;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

public class EdenTransactionInterceptor implements MethodInterceptor {

    private Object target;

    public EdenTransactionInterceptor(final Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy)
            throws Throwable {
        System.out.println("\"HI\" = " + "HI");
        return null;
//        return method.invoke(o, objects);
    }
}
