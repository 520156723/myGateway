package per.hqd.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * 承载配置中的时间
 */
@Data
public class TimeBetweenConfig {
    private LocalTime start;
    private LocalTime end;
}
