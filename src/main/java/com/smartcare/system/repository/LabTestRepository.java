package com.smartcare.system.repository;

import com.smartcare.system.entity.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Integer> {

    // Patient ID එක අනුව Lab Test History එක (කාලානුක්‍රමිකව) ලබා ගැනීම
    List<LabTest> findByPatientPatientIdOrderByTestDateDesc(Integer patientId);
}