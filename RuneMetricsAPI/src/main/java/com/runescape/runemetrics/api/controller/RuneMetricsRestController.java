package com.runescape.runemetrics.api.controller;

import com.runescape.runemetrics.api.service.RuneMetricsService;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RuneMetrics")
public class RuneMetricsRestController {

    private RuneMetricsService runeMetricsService = new RuneMetricsService();

    @GetMapping(value = "/{username}")
    public String getQuestsCompletedByUser(@PathVariable("/{username}") String username) {
        return runeMetricsService.getQuestsCompletedByUser(username);
    }

}