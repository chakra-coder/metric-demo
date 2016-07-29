package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MetricDemoApplication {

    @Bean
    public DemoController demoController(GaugeService gaugeService) {
        return new DemoController(gaugeService);
    }

    @Bean
    @ExportMetricWriter
    public StatsdMetricWriter statsdMetricWriter(
            @Value("${spring.metrics.export.statsd.host}") String host,
            @Value("${spring.metrics.export.statsd.port}") int port,
            @Value("${spring.metrics.export.statsd.prefix}") String prefix
    ) {
        return new StatsdMetricWriter(prefix, host, port);
    }

    public static void main(String[] args) {
        SpringApplication.run(MetricDemoApplication.class, args);
    }
}
