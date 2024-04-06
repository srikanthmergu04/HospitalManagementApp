package com.srikanth.HospitalManagementApp.Controller;

import com.srikanth.HospitalManagementApp.Model.Patient;
import com.srikanth.HospitalManagementApp.Service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/registerPatient")
    public ModelAndView registerPatient(ModelAndView modelAndView) {
        modelAndView.addObject("patient", new Patient());
        modelAndView.setViewName("registerPatient");
        return modelAndView;
    }

    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public ModelAndView addPatient(@ModelAttribute("patient") Patient patient, ModelAndView modelAndView) {
        modelAndView.addObject("patient", patientService.registerPatient(patient));
        modelAndView.setViewName("DisplayPatientDetails");
        return modelAndView;
    }

    @RequestMapping("/patientLogin")
    public ModelAndView patientLogin(HttpServletRequest req, ModelAndView modelAndView) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        String pin = req.getParameter("pin");
        int status = patientService.verifyPatient(pid, pin);

        if (status == 1) {
            modelAndView.addObject("pid", pid);
            modelAndView.setViewName("patientFeatures");

        } else {
            modelAndView.setViewName("errorLogin");
        }
        return modelAndView;
    }

    @RequestMapping("/getPatientsList")
    public String getListOfPatients(Model model) {
        model.addAttribute("list", patientService.getListOfPatients());
        return "patientsDetails";
    }

    @RequestMapping(value = "/bookDoctor", method = RequestMethod.POST)
    public ModelAndView bookDoctor(HttpServletRequest req, ModelAndView modelAndView) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        Integer did = Integer.parseInt(req.getParameter("did"));

        patientService.selectDoctor(pid, did);
        modelAndView.setViewName("appointmentSuccessful");
        return modelAndView;
    }

    @RequestMapping("/patientId")
    public String bookAppointment(HttpServletRequest req, Model model) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        model.addAttribute("pid", pid);
        return "bookAppointment";
    }

    @RequestMapping("/viewPatientProfile")
    public String viewPatientProfileBYId(HttpServletRequest req, Model model) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        Patient patient = patientService.getPatientObject(pid);
        model.addAttribute("patient", patient);
        return "PatientDetailsById";
    }

    @RequestMapping(value = "/updateOrDelete", method = RequestMethod.POST)
    public String performUpdateOrDelete(HttpServletRequest req, Model model) {
        Integer pid = Integer.parseInt(req.getParameter("pid"));
        String action = req.getParameter("action");
        if (action.equals("update")) {
            Patient patient = patientService.getPatientObject(pid);
            model.addAttribute("patient", patient);
            return "updatePatientDetails";
        } else {
            patientService.deletePatientProfile(pid);
            return "patDelSuccess";
        }
    }

    @RequestMapping(value = "/updatePatient", method = RequestMethod.POST)
    public String updatePatient(HttpServletRequest req, @ModelAttribute("patient") Patient patient, Model model) {
        int pid = Integer.parseInt(req.getParameter("pid"));
        patient.setPid(pid);
        model.addAttribute("patient", patientService.updatePatientProfile(patient));
        return "PatientDetailsById";
    }

}
