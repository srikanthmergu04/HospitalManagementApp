package com.srikanth.HospitalManagementApp.Repository;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
