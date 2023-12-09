package student.attendance.system.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Almaty"));
        TimeZone defaultTimeZone = TimeZone.getDefault();

        System.out.println("Default Timezone ID: " + defaultTimeZone.getID());
        System.out.println("Default Timezone Display Name: " + defaultTimeZone.getDisplayName());
        System.out.println("Default Timezone Offset: " + defaultTimeZone.getRawOffset() / (60 * 60 * 1000) + " hours");

        SpringApplication.run(Application.class, args);
    }

}
