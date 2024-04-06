package com.srikanth.HospitalManagementApp.Dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientDaoImpl implements PatientDao {

    @Autowired
    SessionFactory sessionFactory;

    public void registerPatient(Patient patient) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Transaction tran = session.beginTransaction();

        session.save(patient);

        tran.commit();

        session.close();

    }

    public Patient getPatientObject(Integer pid) {
        // TODO Auto-generated method stub


        Session session = sessionFactory.openSession();

        return (Patient) session.get(Patient.class, pid);


    }

    public List<Patient> getListOfPatients() {
        // TODO Auto-generated method stub

        List<Patient> list = new ArrayList();

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Patient");

        list = query.list();

        session.close();

        return list;
    }

    public void selectDoctor(Integer pid, Integer did) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Transaction tran = session.beginTransaction();

        Patient patient = (Patient) session.get(Patient.class, pid);

        Doctor doctor = (Doctor) session.get(Doctor.class, did);

        Set<Patient> patList = new HashSet<Patient>();

        if (doctor.getPatList() == null) {
            doctor.setPatList(patList);
        }

        doctor.getPatList().add(patient);

        session.update(doctor);

        tran.commit();

        session.close();


    }

    public void deletePatientProfile(Integer pid) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Transaction tran = session.beginTransaction();

        Patient patient = (Patient) session.get(Patient.class, pid);

        session.delete(patient);

        tran.commit();

        session.close();

    }

    public int updatePatientProfile(Patient patient) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Transaction tran = session.beginTransaction();

        session.update(patient);

        return 0;
    }

}
