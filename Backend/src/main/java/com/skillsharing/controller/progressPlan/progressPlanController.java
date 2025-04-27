package com.skillsharing.controller.progressPlan;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillsharing.dto.progressPlan.progressPlanDTO;
import com.skillsharing.services.progressPlan.progressPlanService;

@RestController
@CrossOrigin
@RequestMapping("/api/progressPlan/")
public class progressPlanController {

    @Autowired
    private progressPlanService progressPlanService;

    // Get all learning plans
    @GetMapping("/getallprogressplans")
    public List<progressPlanDTO> getAllprogressPlans() {
        return progressPlanService.getAllProgressPlans();
    }

    // Get a learning plan by ID
    @GetMapping("/getallprogressplansbyId/{id}")
    public progressPlanDTO getprogressPlanById(@PathVariable ObjectId id) {
        return progressPlanService.getProgressPlanById(id);
    }

    // Create a new learning plan
    @PostMapping("/addprogressPlan")
    public progressPlanDTO createprogressPlan(@RequestBody progressPlanDTO progressPlanDTO) {
        return progressPlanService.createProgressPlan(progressPlanDTO);
    }

    // Update a learning plan
    @PutMapping("/updateprogressPlan/{id}")
    public progressPlanDTO updateprogressPlan(@PathVariable ObjectId id, @RequestBody progressPlanDTO progressPlanDTO) {
        return progressPlanService.updateProgressPlan(id, progressPlanDTO);
    }

    // Delete a learning plan
    @DeleteMapping("/deleteprogressPlan/{id}")
    public String deleteProgressPlan(@PathVariable ObjectId id) {
        return progressPlanService.deleteProgressPlan(id);
    }
}
