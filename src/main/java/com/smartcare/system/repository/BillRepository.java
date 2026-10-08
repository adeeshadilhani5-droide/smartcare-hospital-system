package com.smartcare.system.repository;

import com.smartcare.system.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    // Patient ID එක අනුව Bills  ලබා ගැනීම
    List<Bill> findByPatientPatientIdOrderByBillDateDesc(Integer patientId);
}