package com.srikanth.HospitalManagementApp.Service;

import com.srikanth.HospitalManagementApp.Model.Patient;

import java.util.List;


public interface PatientService {

    Patient registerPatient(Patient patient);

    int verifyPatient(Integer pid, String pin);

    List<Patient> getListOfPatients();

    void selectDoctor(Integer pid, Integer did);

    Patient getPatientObject(Integer pid);

    void deletePatientProfile(Integer pid);

    Patient updatePatientProfile(Patient patient);
}
