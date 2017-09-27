package pl.atk.shopisticated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Shopisticated backend main class.
 */
@SpringBootApplication
public class ShopisticatedApplication {

    public static void main(String[] args) {
        new SpringApplication(ShopisticatedApplication.class).run(args);
    }
}
