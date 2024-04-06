package com.srikanth.HospitalManagementApp.Service;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Model.Patient;

import java.util.List;
import java.util.Set;


public interface DoctorService {

    void registerDoctor(Doctor doctor);

    int verifyDoctor(Integer pid, String pin);

    List<Doctor> getDoctorsBySpecialization(String specialization);

    List<Doctor> getListOfDoctors();

    Set<Patient> getPatientsAppointmentsList(int did);
}
