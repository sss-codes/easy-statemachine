package fans.java.esm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"fans.java"})
public class EasyStatemachineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyStatemachineDemoApplication.class, args);
    }

}
