package cn.saintshaga.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by huang on 18-8-13.
 */
@Service
@Slf4j
public class Scheduler {

//    @Scheduled(fixedRate = 5000)
    public void checkConnection() {
        log.warn("The time is now {}", new Date());
    }
}
