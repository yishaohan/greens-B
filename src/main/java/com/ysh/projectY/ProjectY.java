package com.ysh.projectY;

import com.ysh.projectY.listener.EventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
public class ProjectY {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ProjectY.class);
//        springApplication.addListeners(new EventListener());
        springApplication.run(args);
    }

}
