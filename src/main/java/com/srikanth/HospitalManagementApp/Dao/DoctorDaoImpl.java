package com.srikanth.HospitalManagementApp.Dao;

import java.util.ArrayList;
import java.util.List;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DoctorDaoImpl implements DoctorDao {

    @Autowired
    SessionFactory sessionFactory;

    public void registerDoctor(Doctor doctor) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Transaction tran = session.beginTransaction();

        session.save(doctor);

        tran.commit();

        session.close();


    }

    public Doctor getDoctorObject(Integer pid) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        Doctor doctor = (Doctor) session.get(Doctor.class, pid);

        //	session.close();

        /* return (Doctor) session.get(Doctor.class, pid); */

        return doctor;


    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        // TODO Auto-generated method stub

        Session session = sessionFactory.openSession();

        List<Doctor> list = new ArrayList();

        Query query = session.createQuery("from Doctor d where d.specialization = :spe");

        query.setParameter("spe", specialization);

        System.out.println("getDoctorsBySpecialization method in DoctorDaoImpl");

        list = query.list();

        /*
         * for (Doctor doctor : list) { System.out.println("name = "+doctor.getName());
         * System.out.println("city = "+doctor.getCity()); }
         */
        session.close();
        return list;
    }

    public List<Doctor> getListOfDoctors() {
        // TODO Auto-generated method stub

        List<Doctor> list = new ArrayList();

        Session session = sessionFactory.openSession();

        Query query = session.createQuery("from Doctor");

        list = query.list();

        return list;
    }

}
