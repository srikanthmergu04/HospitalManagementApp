package com.srikanth.HospitalManagementApp.Service;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Model.Patient;
import com.srikanth.HospitalManagementApp.Repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor registerDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public int verifyDoctor(Integer did, String pin) {
        Doctor doctor = doctorRepository.findById(did).orElse(null);

        if (ObjectUtils.isEmpty(doctor)) {
            return -1;
        }
        if (pin.equals(doctor.getPin())) {
            return 1;
        } else {
            return -1;
        }
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findAll().stream().filter(doctor -> doctor.getSpecialization().equals(specialization)).toList();
    }

    public List<Doctor> getListOfDoctors() {
        return doctorRepository.findAll();
    }

    public Set<Patient> getPatientsAppointmentsList(int did) {
        Doctor doctor = doctorRepository.findById(did).orElse(null);
        if (ObjectUtils.isEmpty(doctor)) {
            return new HashSet<>();
        }
        return doctor.getPatList();
    }

}
