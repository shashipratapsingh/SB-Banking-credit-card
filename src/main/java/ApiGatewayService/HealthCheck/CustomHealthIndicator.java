package ApiGatewayService.HealthCheck;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom health logic
        return Health.up().withDetail("custom", "Custom Health Indicator, APi Gateway").build();
    }
}
