package aop.stage0;

import aop.DataAccessException;
import aop.Transactional;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionHandler implements InvocationHandler {

    private final PlatformTransactionManager transactionManager;
    private final Object target;

    public TransactionHandler(final PlatformTransactionManager transactionManager, final Object target) {
        this.transactionManager = transactionManager;
        this.target = target;
    }

    /**
     * @Transactional 어노테이션이 존재하는 메서드만 트랜잭션 기능을 적용하도록 만들어보자.
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        System.out.println("declaredMethods = " + Arrays.toString(declaredMethods));
//        Method temp = target.getClass().getDeclaredMethod(method.toGenericString());
        System.out.println("method.toGenericString() = " + method.toGenericString());
        System.out.println("method.getParameterTypes() = " + Arrays.toString(method.getParameterTypes()));
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method temp2 = target.getClass().getDeclaredMethod(method.toGenericString(), parameterTypes[0], parameterTypes[1], parameterTypes[2]);
        System.out.println("temp2 = " + temp2);
//        System.out.println("temp = " + temp);
//        Method[] targetMethods = target.getClass().getDeclaredMethod(method);
//        Annotation[] annotations = temp.getAnnotations();
//        Annotation[] declaredAnnotations = temp.getDeclaredAnnotations();
//        System.out.println("annotations = " + Arrays.toString(annotations));
//        System.out.println("declaredAnnotations = " + Arrays.toString(declaredAnnotations));
        if (method != null) {
            TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                Object returnValue = method.invoke(target, args);
                transactionManager.commit(transactionStatus);
                return returnValue;
            } catch (DataAccessException e) {
                transactionManager.rollback(transactionStatus);
                throw new DataAccessException();
            }
        }
        return null;
    }
}
