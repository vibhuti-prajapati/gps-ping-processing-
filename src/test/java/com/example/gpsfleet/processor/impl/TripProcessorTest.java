package com.example.gpsfleet.processor.impl;

import com.example.gpsfleet.entity.Device;
import com.example.gpsfleet.entity.GpsPing;
import com.example.gpsfleet.repository.AlertRepository;
import com.example.gpsfleet.repository.DeviceRepository;
import com.example.gpsfleet.repository.GpsPingRepository;
import com.example.gpsfleet.repository.TripRepository;
import com.example.gpsfleet.processor.TripProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan(basePackages = "com.example.gpsfleet.entity")
@EnableJpaRepositories(basePackages = "com.example.gpsfleet.repository")
@TestPropertySource(properties = {
        "spring.security.enabled=false"
})
@ActiveProfiles("test")
class TripProcessorTest {

    @TestConfiguration
    static class NoThreadProcessorConfig {
        @Bean
        @Primary
        public TripProcessor tripProcessor(TripProcessorImpl base) {
            return new TripProcessor() {

                @Override
                public void enqueue(GpsPing ping) {
                    // call your internal logic directly (synchronous)
                    base.processDirect(ping);
                }

                @Override
                public void start() {
                    // do nothing, no background thread
                }

                @Override
                public void stop() {
                    // do nothing
                }
            };
        }
    }

    @Autowired
    private TripProcessor tripProcessor;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private GpsPingRepository pingRepository;
    @Test
    void testTripStart() throws Exception {
        long count = tripRepository.count();
        Device device = deviceRepository.findDeviceByDeviceId("dev-1001");
        Instant base = Instant.now();
        if(device!=null) {
            GpsPing p1 = new GpsPing();
            p1.setDevice(device);
            p1.setLat(19.0760);
            p1.setLon(72.8777);
            p1.setSpeedKmh(0.0);
            p1.setHeading(0.0);
            p1.setSentAt(base);

            GpsPing p2 = new GpsPing();
            p2.setDevice(device);
            p2.setLat(19.0760);
            p2.setLon(72.8777);
            p2.setSpeedKmh(1.0);
            p2.setHeading(0.0);
            p2.setSentAt(base.plusSeconds(15));

            GpsPing p3 = new GpsPing();
            p3.setDevice(device);
            p3.setLat(19.0765);
            p3.setLon(72.8782);
            p3.setSpeedKmh(15.0);
            p3.setSentAt(base.plusSeconds(30));

            GpsPing p4 = new GpsPing();
            p4.setDevice(device);
            p4.setLat(19.0770);
            p4.setLon(72.8787);
            p4.setSpeedKmh(22.0);
            p4.setHeading(20.0);
            p4.setSentAt(base.plusSeconds(45));

            GpsPing p5 = new GpsPing();
            p5.setDevice(device);
            p5.setLat(19.0780);
            p5.setLon(72.8795);
            p5.setSpeedKmh(30.0);
            p5.setHeading(25.0);
            p5.setSentAt(base.plusSeconds(60));

            GpsPing p6 = new GpsPing();
            p6.setDevice(device);
            p6.setLat(19.0782);
            p6.setLon(72.8796);
            p6.setSpeedKmh(3.0);
            p6.setHeading(25.0);
            p6.setSentAt(base.plusSeconds(75));

            GpsPing p7 = new GpsPing();
            p7.setDevice(device);
            p7.setLat(19.0782);
            p7.setLon(72.8796);
            p7.setSpeedKmh(0.0);
            p7.setHeading(25.0);
            p7.setSentAt(base.plusSeconds(100));

            GpsPing p8 = new GpsPing();
            p8.setDevice(device);
            p8.setLat(19.0782);
            p8.setLon(72.8796);
            p8.setSpeedKmh(0.0);
            p8.setHeading(0.0);
            p8.setSentAt(base.plusSeconds(500));

            tripProcessor.enqueue(p1);
            tripProcessor.enqueue(p2);
            tripProcessor.enqueue(p3);
            tripProcessor.enqueue(p4);
            tripProcessor.enqueue(p5);
            tripProcessor.enqueue(p6);
            tripProcessor.enqueue(p7);
            tripProcessor.enqueue(p8);
            // give processor thread time to process queue
//            Thread.sleep(1000);

            assertThat(tripRepository.count()).isEqualTo(count + 1);
        }
    }
}
