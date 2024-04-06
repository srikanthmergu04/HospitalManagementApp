package com.srikanth.HospitalManagementApp.Controller;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Model.Patient;
import com.srikanth.HospitalManagementApp.Service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RequestMapping("/registerDoctor")
    public String registerDoctor(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "registerDoctor";

    }

    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {
        doctorService.registerDoctor(doctor);

        model.addAttribute("doctor", doctor);

        return "DisplayDoctorDetails";

    }

    @RequestMapping("/doctorLogin")
    public ModelAndView doctorLogin(HttpServletRequest req, ModelAndView modelAndView) {
        Integer did = Integer.parseInt(req.getParameter("did"));

        String pin = req.getParameter("pin");

        int status = doctorService.verifyDoctor(did, pin);

        if (status == 1) {
            modelAndView.addObject("did", did);
            modelAndView.setViewName("doctorFeatures");
            return modelAndView;

        } else {
            modelAndView.setViewName("errorDoctorLogin");
            return modelAndView;
        }


    }

    @RequestMapping("/bookAppointment")
    public String getDoctorsListBySpecialization(HttpServletRequest req, Model model) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));

        System.out.println("pid = " + pid);

        String specialization = req.getParameter("specialization");

        System.out.println(" Specialization =  " + specialization);

        List<Doctor> list = new ArrayList();

        list = doctorService.getDoctorsBySpecialization(specialization);
        model.addAttribute("pid", pid);

        model.addAttribute("list", list);

        /*
         * System.out.println("list  = "+list);
         *
         * for (Doctor doctor : list) { System.out.println("name = "+doctor.getName());
         * System.out.println("city = "+doctor.getCity()); }
         */

        return "DoctosListBySpecialization";

    }

    @RequestMapping("/getDoctorsList")
    public String getListOfDoctors(Model model) {

        List<Doctor> list = new ArrayList();

        list = doctorService.getListOfDoctors();

        model.addAttribute("list", list);

        return "doctorsDetails";

    }

    @RequestMapping("/patientAppointments")
    public String getListOfPatientAppointments(HttpServletRequest req, Model model) {
        int did = Integer.parseInt(req.getParameter("did"));

        System.out.println("did = " + did);

        Set<Patient> list = new HashSet<Patient>();

        list = doctorService.getPatientsAppointmentsList(did);

        model.addAttribute("list", list);

        /*
         * for (Patient patient : list) {
         * System.out.println("name = "+patient.getPname());
         * System.out.println("city = "+patient.getCity()); }
         */

        return "patAppointmentDetails";

    }

}
