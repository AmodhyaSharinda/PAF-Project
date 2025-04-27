package com.skillsharing.repository.progressPlan;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.skillsharing.model.progressPlan.progressPlan;

@Repository
public interface ProgressPlanRepository extends MongoRepository<progressPlan, ObjectId> {
    
    List<progressPlan> findBytitleContainingIgnoreCase(String keyword);

}

