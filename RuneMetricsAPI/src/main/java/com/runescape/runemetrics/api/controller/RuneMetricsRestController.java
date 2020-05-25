package com.runescape.runemetrics.api.controller;

import com.runescape.runemetrics.api.service.RuneMetricsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RuneMetrics")
public class RuneMetricsRestController {

    private RuneMetricsService runeMetricsService = new RuneMetricsService();

    @RequestMapping(value = "/getQuestsCompletedByUser/{username}", method = RequestMethod.GET)
    public String getQuestsCompletedByUser(@PathVariable String username) {
        return runeMetricsService.getQuestsCompletedByUser(username);
    }

    // TODO: Find a way to pass quest title as a pre-defined field from a list of quest titles
    @RequestMapping(value = "/getQuestEligibility/{username}/{questTitle}", method = RequestMethod.GET)
    public String getQuestEligibility(@PathVariable String username, @PathVariable String questTitle) {
        return runeMetricsService.getQuestEligibility(username, questTitle);
    }

    @RequestMapping(value = "/getTotalNumberOfCompletedQuests/{username}", method = RequestMethod.GET)
    public int getTotalNumberOfCompletedQuests(@PathVariable String username) {
        return runeMetricsService.getTotalNumberOfCompletedQuests(username);
    }

}