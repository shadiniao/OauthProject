package com.zyz.web.controller;

import com.zyz.web.async.DeferredResultHolder;
import com.zyz.web.async.MockQueue;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * 2018/9/3.
 *
 * @author zhangyizhi
 */
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order1")
    public Callable<String> order1() throws InterruptedException {
        logger.info("主线程开始");
//        Callable<String> result = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "success";
//            }
//        };

        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(3000);
            logger.info("副线程结束");
            return "success";
        };

        logger.info("主线程结束");
        return result;
    }

    @RequestMapping("/order2")
    public DeferredResult<String> order2() throws InterruptedException {
        logger.info("主线程开始");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

        logger.info("主线程结束");
        return result;
    }
}
