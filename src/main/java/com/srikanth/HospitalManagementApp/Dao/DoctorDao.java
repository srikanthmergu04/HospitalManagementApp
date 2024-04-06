package com.srikanth.HospitalManagementApp.Dao;


import com.srikanth.HospitalManagementApp.Model.Doctor;

import java.util.List;

public interface DoctorDao {

    void registerDoctor(Doctor doctor);

    Doctor getDoctorObject(Integer pid);

    List<Doctor> getDoctorsBySpecialization(String specialization);

    List<Doctor> getListOfDoctors();
}
