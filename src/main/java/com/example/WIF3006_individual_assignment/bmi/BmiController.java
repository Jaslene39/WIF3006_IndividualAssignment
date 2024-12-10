package com.example.WIF3006_individual_assignment.bmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/bmi")
public class BmiController {

    private final BmiServiceImpl bmiService;

    @Autowired
    public BmiController(BmiServiceImpl bmiService) {
        this.bmiService = bmiService;
    }

    // get all BMI data of users
    @GetMapping(path = "/getAllBmiData")
    public List<Bmi> getAllBmiData(){
        return bmiService.getAllBmiData();
    }

    // get BMI data by user Id
    @GetMapping(path = "/getBmiByUserId/{userId}")
    public ResponseEntity<Bmi> getBmiDataByUserId(@PathVariable("userId") Long userId) {
        return bmiService.getBmiDataByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // add new BMI data
    @PostMapping(path = "/addBmiData")
    public void addBmiData(@RequestBody Bmi bmi) {
        bmiService.addBmiData(bmi);
    }

    // delete BMI data by bmi Id
    @DeleteMapping(path = "/deleteBmiData/{bmiId}")
    public void deleteBmiData(@PathVariable("bmiId") Long bmiId) {
        bmiService.deleteBmiData(bmiId);
    }

    //update BMI data by user Id
    @PutMapping(path = "/updateBmi/{userId}")
    public void updateBmi(@PathVariable("userId") Long userId,
                          @RequestParam(required = false) Integer height,
                          @RequestParam(required = false) Integer weight) {
        bmiService.updateBmi(userId, height, weight);
    }
}
