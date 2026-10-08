package com.smartcare.system.repository;

import com.smartcare.system.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    // Patient ID එක අනුව Medical History එක (කාලානුක්‍රමිකව) ලබා ගැනීම
    List<Treatment> findByPatientPatientIdOrderByTreatmentDateDesc(Integer patientId);
}