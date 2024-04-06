package com.srikanth.HospitalManagementApp.Dao;

import com.srikanth.HospitalManagementApp.Model.Patient;

import java.util.List;

public interface PatientDao {

    void registerPatient(Patient patient);

    Patient getPatientObject(Integer pid);

    List<Patient> getListOfPatients();

    void selectDoctor(Integer pid, Integer did);

    void deletePatientProfile(Integer pid);

    int updatePatientProfile(Patient patient);

}
