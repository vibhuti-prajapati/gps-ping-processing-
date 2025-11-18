package com.example.gpsfleet.service.impl;


import com.example.gpsfleet.service.TripProcessor;
import com.example.gpsfleet.entity.GpsPing;
import com.example.gpsfleet.repository.GpsPingRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Component
public class TripProcessorImpl implements TripProcessor {


    private final LinkedBlockingQueue<GpsPing> queue = new LinkedBlockingQueue<>(10000);
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private volatile boolean running = false;


    private final GpsPingRepository gpsPingRepository;


    @Autowired
    public TripProcessorImpl(GpsPingRepository gpsPingRepository) {
        this.gpsPingRepository = gpsPingRepository;
    }


    @Override
    public void enqueue(GpsPing ping) {
        if (!queue.offer(ping)) {
// fallback: log and drop or persist for later
            System.err.println("TripProcessor queue full — dropping ping: " + ping.getPingId());
        }
    }


    @PostConstruct
    @Override
    public void start() {
        running = true;
        worker.submit(() -> {
            while (running || !queue.isEmpty()) {
                try {
                    GpsPing p = queue.poll(1, TimeUnit.SECONDS);
                    if (p == null) continue;
                    process(p);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private void process(GpsPing ping) {
// Minimal placeholder processing: you should replace with full trip segmentation
// For now we just print and could update related aggregates
        System.out.println("Processing ping: " + ping.getPingId() + " device=" + ping.getDevice());
// Example: you may load last ping for device and compute distance
    }


    @PreDestroy
    @Override
    public void stop() {
        running = false;
        worker.shutdown();
        try { worker.awaitTermination(5, TimeUnit.SECONDS); } catch (InterruptedException ignored) {}
    }
}