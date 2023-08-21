package com.wyhcode;

import cn.hutool.core.lang.ClassScanner;
import com.wyhcode.biz.CarService;
import com.wyhcode.biz.UserService;
import com.wyhcode.core.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author weiyuhui
 * @date 2023/8/21 14:12
 * @description
 *  推荐指数：★★★★★
 *  <p>
 *  单例是一种创建型设计模式，让你能够保证一个类只有一个实例，并提供一个访问实例的全局节点。
 *  <p>
 *  模拟容器的单例池
 */

@Slf4j
public class TestSingleTon {

    /**
     * 线程池
     */
    public static final ExecutorService EXECUTOR_GENERAL = new ThreadPoolExecutor(100,1000,
            0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());

    @Test
    public void test01() throws InterruptedException {
        synchronized (TestSingleTon.class){
            test(false);
        }
    }

    public void test(boolean isLasy) throws InterruptedException {
        Set<Class<?>> classes = ClassScanner.scanPackage("com.wyhcode.biz");
        ApplicationContext applicationContext = new ApplicationContext(classes, isLasy);
        log.info("懒加载模式：{}  容器bean数量：{}",isLasy,applicationContext.getTargetMap().size());

        // 并发拿对象
        int size = 1000;
        List<CarService> carServices = new ArrayList<>(size);
        List<UserService> userServices = new ArrayList<>(size);
        CountDownLatch countDownLatch = new CountDownLatch(size);
        EXECUTOR_GENERAL.execute(()->{
            try {
                log.info("cur Thead:{}",Thread.currentThread().getName());
                UserService userService = applicationContext.getBean(UserService.class);
                userServices.add(userService);
                CarService carService = applicationContext.getBean(CarService.class);
                carServices.add(carService);
            } catch (Exception ignore) {

            }finally {
                countDownLatch.countDown();
            }
        });
        boolean await = countDownLatch.await(5, TimeUnit.SECONDS);
        log.info("UserServices Size : {}",userServices.size());
        log.info("CarServices Size : {}",carServices.size());
    }
}
