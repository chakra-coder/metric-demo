package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 */
@Controller
public class DemoController {

    private final GaugeService gaugeService;

    @Autowired
    public DemoController(GaugeService gaugeService) {
        this.gaugeService = gaugeService;
    }

    @RequestMapping(value = "/", produces = "text/plain")
    @ResponseBody
    public String index() {
        final long start = System.nanoTime();

        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            // NOPE
        }

        final long end = System.nanoTime();
        gaugeService.submit("timer.test", TimeUnit.NANOSECONDS.toMillis(end - start));

        return "This is the index!";
    }
}
