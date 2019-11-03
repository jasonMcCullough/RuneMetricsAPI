package com.runescape.runemetrics.api;

import com.runescape.runemetrics.api.controller.RuneMetricsRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuneMetricsAPI {

    public static void main(String[] args) {
        SpringApplication.run(RuneMetricsAPI.class, args);
        //RuneMetricsRestController controller = new RuneMetricsRestController();
        //System.out.print(controller.getQuestsCompletedByUser("Hypa Jim"));
    }

}
