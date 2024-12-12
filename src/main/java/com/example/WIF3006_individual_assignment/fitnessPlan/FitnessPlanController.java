package com.example.WIF3006_individual_assignment.fitnessPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fitnessPlan")
public class FitnessPlanController {

    private final FitnessPlanServiceImpl fitnessPlanService;

    @Autowired
    public FitnessPlanController(FitnessPlanServiceImpl fitnessPlanService) {
        this.fitnessPlanService = fitnessPlanService;
    }

    // get all fitness plans of users
    @GetMapping(path = "/getAllFitnessPlans")
    public List<FitnessPlan> getAllFitnessPlans() {
        return this.fitnessPlanService.getAllFitnessPlans();
    }

    // get fitness plan by user Id
    @GetMapping(path = "/getFitnessPlansByUserId/{userId}")
    public List<FitnessPlan> getFitnessPlansByUserId(@PathVariable("userId") Long userId) {
        return fitnessPlanService.getFitnessPlansByUserId(userId);
    }

    // add new fitness plan
    @PostMapping(path = "/addNewFitnessPlan")
    public void addNewFitnessPlan(@RequestBody FitnessPlan fitnessPlan) {
        fitnessPlanService.addNewFitnessPlan(fitnessPlan);
    }

    // delete fitness plan by fitness plan Id
    @DeleteMapping(path = "/deleteFitnessPlan/{fitnessPlanId}")
    public void deleteFitnessPlan(@PathVariable("fitnessPlanId") Long fitnessPlanId) {
        fitnessPlanService.deleteFitnessPlan(fitnessPlanId);
    }

    // update fitness plan by user Id && fitness plan Id
    @PutMapping(path = "/updateFitnessPlan/{userId}/{fitnessPlanId}")
    public void updateFitnessPlan(@PathVariable("userId") Long userId,
                                  @PathVariable("fitnessPlanId") Long fitnessPlanId,
                                  @RequestParam(required = false) String planTitle,
                                  @RequestParam(required = false) String planDesc,
                                  @RequestParam(required = false) String goal,
                                  @RequestParam(required = false) String duration,
                                  @RequestParam(required = false) String difficultyLevel) {
        fitnessPlanService.updateFitnessPlan(userId, fitnessPlanId, planTitle, planDesc, goal ,duration, difficultyLevel);
    }
}
