package com.runescape.runemetrics.api.controller;

import com.runescape.runemetrics.api.service.RuneMetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RuneMetrics")
public class RuneMetricsRestController {

    private RuneMetricsService runeMetricsService = new RuneMetricsService();

    @GetMapping(value = "/{username}")
    public String getQuestsCompletedByUser(@PathVariable String username) {
        return runeMetricsService.getQuestsCompletedByUser(username);
    }

    // TODO: Find a way to pass quest title as a pre-defined field from a list of quest titles
    @GetMapping(value = "/{username}/{questTitle}")
    public String getQuestEligibility(@PathVariable String username, @PathVariable String questTitle) {
        return runeMetricsService.getQuestEligibility(username, questTitle);
    }

}