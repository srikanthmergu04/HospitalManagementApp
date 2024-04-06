package com.srikanth.HospitalManagementApp.Service;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Model.Patient;
import com.srikanth.HospitalManagementApp.Repository.DoctorRepository;
import com.srikanth.HospitalManagementApp.Repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public int verifyPatient(Integer pid, String pin) {
        Patient patient = patientRepository.findById(pid).orElse(null);

        if (ObjectUtils.isEmpty(patient)) {
            return -1;
        }
        if (pin.equals(patient.getPin())) {
            return 1;
        } else {
            return -1;
        }
    }

    public List<Patient> getListOfPatients() {
        return patientRepository.findAll();
    }

    public void selectDoctor(Integer pid, Integer did) {
        Patient patient = patientRepository.findById(pid).orElse(null);
        Doctor doctor = doctorRepository.findById(did).orElse(null);

        if (!ObjectUtils.isEmpty(doctor)) {
            doctor.getPatList().add(patient);
            doctorRepository.save(doctor);
        }
    }

    public Patient getPatientObject(Integer pid) {
        return patientRepository.findById(pid).orElse(null);
    }

    public void deletePatientProfile(Integer pid) {
        patientRepository.deleteById(pid);
    }

    public Patient updatePatientProfile(Patient patient) {
        return patientRepository.save(patient);
    }

}
