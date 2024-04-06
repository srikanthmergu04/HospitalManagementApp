package com.srikanth.HospitalManagementApp.Controller;

import com.srikanth.HospitalManagementApp.Model.Doctor;
import com.srikanth.HospitalManagementApp.Service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


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
        model.addAttribute("doctor", doctorService.registerDoctor(doctor));
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

        } else {
            modelAndView.setViewName("errorDoctorLogin");
        }
        return modelAndView;
    }

    @RequestMapping("/bookAppointment")
    public String getDoctorsListBySpecialization(HttpServletRequest req, Model model) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        String specialization = req.getParameter("specialization");
        model.addAttribute("pid", pid);
        model.addAttribute("list", doctorService.getDoctorsBySpecialization(specialization));
        return "DoctorsListBySpecialization";
    }

    @RequestMapping("/getDoctorsList")
    public String getListOfDoctors(Model model) {
        model.addAttribute("list", doctorService.getListOfDoctors());
        return "doctorsDetails";
    }

    @RequestMapping("/patientAppointments")
    public String getListOfPatientAppointments(HttpServletRequest req, Model model) {
        int did = Integer.parseInt(req.getParameter("did"));
        model.addAttribute("list", doctorService.getPatientsAppointmentsList(did));
        return "patAppointmentDetails";
    }

}
