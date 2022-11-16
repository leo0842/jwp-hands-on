package aop.stage1;

import aop.DataAccessException;
import aop.StubUserHistoryDao;
import aop.domain.User;
import aop.repository.UserDao;
import aop.repository.UserHistoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Stage1Test {

    private static final Logger log = LoggerFactory.getLogger(Stage1Test.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserHistoryDao userHistoryDao;

    @Autowired
    private StubUserHistoryDao stubUserHistoryDao;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @BeforeEach
    void setUp() {
        final var user = new User("gugu", "password", "hkkang@woowahan.com");
        userDao.insert(user);
    }

    @Test
    void testChangePassword() {
        System.out.println("\"HI\" = " + "HI");
//        UserService userService = registerProxyFactoryBean(new UserService(userDao, userHistoryDao));
        final var newPassword = "qqqqq";
        final var createBy = "gugu";

//        UserService userService1 = (UserService) Enhancer.create(
//                UserService.class,
//                new EdenTransactionInterceptor(new UserService())
//        );
//        edenController.test();
//        userService.changePassword(1L, newPassword, createBy);
//        final var actual = userService.findById(1L);
////        User byId = userService1.findById(1L);
//        assertThat(actual.getPassword()).isEqualTo(newPassword);
    }

//    @Test
//    void testTransactionRollback() {
//        final UserService userService = registerProxyFactoryBean(new UserService(userDao, stubUserHistoryDao));
//
//        final var newPassword = "newPassword";
//        final var createBy = "gugu";
//        assertThrows(DataAccessException.class,
//                () -> userService.changePassword(1L, newPassword, createBy));
//
//        final var actual = userService.findById(1L);
//
//        assertThat(actual.getPassword()).isNotEqualTo(newPassword);
//    }

//    private UserService registerProxyFactoryBean(final UserService userService) {
//        final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTarget(userService);
////        TransactionAdvice advice = new TransactionAdvice(platformTransactionManager);
////        TransactionPointcut pointcut = new TransactionPointcut();
////        proxyFactoryBean.addAdvisor(new TransactionAdvisor(advice, pointcut));
////        proxyFactoryBean.setProxyTargetClass(true);
//        return (UserService) proxyFactoryBean.getObject();
//    }

//    private void registerProxyFactoryBean2(final UserService userService) {
//        final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTarget(userService);
//        TransactionAdvice advice = new TransactionAdvice(platformTransactionManager);
//        TransactionPointcut pointcut = new TransactionPointcut();
//        proxyFactoryBean.addAdvisor(new TransactionAdvisor(advice, pointcut));
//        proxyFactoryBean.setProxyTargetClass(true);
//        return (UserService) proxyFactoryBean.getObject();
//    }
}
