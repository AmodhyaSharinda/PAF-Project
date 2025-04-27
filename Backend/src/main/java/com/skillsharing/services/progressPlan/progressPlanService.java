package com.skillsharing.services.progressPlan;

import java.util.List;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skillsharing.dto.progressPlan.progressPlanDTO;
import com.skillsharing.model.progressPlan.progressPlan;
import com.skillsharing.repository.progressPlan.ProgressPlanRepository;

@Service
@Transactional
public class progressPlanService {

    @Autowired
    private ProgressPlanRepository progressPlanRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get all progress plans
    public List<progressPlanDTO> getAllProgressPlans() {
        List<progressPlan> plans = progressPlanRepository.findAll();
        return modelMapper.map(plans, new TypeToken<List<progressPlanDTO>>() {}.getType());
    }

    // Get a single progress plan by ID
    public progressPlanDTO getProgressPlanById(ObjectId id) {
        progressPlan plan = progressPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress Plan not found"));
        return modelMapper.map(plan, progressPlanDTO.class);
    }

    // Create a new progress plan
    public progressPlanDTO createProgressPlan(progressPlanDTO progressPlanDTO) {
        progressPlanRepository.save(modelMapper.map(progressPlanDTO, progressPlan.class));
        return progressPlanDTO;
    }

    // Update an existing progress plan
    public progressPlanDTO updateProgressPlan(ObjectId id, progressPlanDTO progressPlanDTO) {
        progressPlan existingPlan = progressPlanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress Plan not found"));

        progressPlan updatedPlan = modelMapper.map(progressPlanDTO, progressPlan.class);
        updatedPlan.setId(existingPlan.getId()); // Ensure correct id is set
        progressPlanRepository.save(updatedPlan);

        return progressPlanDTO;
    }

    // Delete a progress plan
    public String deleteProgressPlan(ObjectId id) {
        progressPlanRepository.deleteById(id);
        return "Progress Plan successfully deleted.";
    }

   
}