package ro.sci.gms.web;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ro.sci.gms.domain.Blood;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Gender;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.PatientService;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private Patient loggedPatient;
	@Autowired
	private User loggedUser;

	@RequestMapping("")
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("user");
		modelAndView.addObject("user", loggedPatient);

		return modelAndView;
	}

	@RequestMapping("/patient/profile")
	public ModelAndView editPatientProfile() {

		Patient patient = patientService.getPatient(11226L); //10 on heroku
		
		ModelAndView modelAndView = new ModelAndView("patientedit");
		modelAndView.addObject("patient", patient);

		return modelAndView;
	}
	
	@RequestMapping("/doctor/profile")
	public ModelAndView editDoctorProfile() {

		Patient doctor = patientService.getPatient(10L); //10L on heroku //11226L on localhost
		
		ModelAndView modelAndView = new ModelAndView("patientedit");
		modelAndView.addObject("patient", doctor);

		return modelAndView;
	}


	@RequestMapping("/patient")
	public String indexPatient() {
		return "user";
	}

	@RequestMapping(value="/patient/profile", method = RequestMethod.POST)
	public String save(@ModelAttribute Patient patient) throws ValidationException, SQLException {
		patient.setId(10L); //10L on heroku //11226L on localhost
		patient.setRole(Role.user);
		patient.setDateOfBirth(new Date());
		patient.setGender(Gender.FEMALE);
		patient.setBloodType(Blood.A);
		patient.setDoctor(new Doctor());
		
		patientService.save(patient);
		patient.see();
		return "success";
	}
	
	@RequestMapping(value="/patient/profile", method = RequestMethod.POST)
	public String saveDoctor_patientStill(@ModelAttribute Patient patient) throws ValidationException, SQLException {
		patient.setId(10L); //10L on heroku //11226L on localhost
		patient.setRole(Role.user);
		patient.setDateOfBirth(new Date());
		patient.setGender(Gender.FEMALE);
		patient.setBloodType(Blood.AB);
		patient.setDoctor(new Doctor());
		
		patientService.save(patient);
		patient.see();
		return "success";
	}
	
	
	
	@RequestMapping(value="/generate")
	public String generatePatient() throws ValidationException, SQLException {
		Patient patient = new Patient();
		patient.setFirstName("Angela");
		patient.setLastName("Merkel");
		patient.setUsername("angela.merkel");
		patient.setPassword("DeutschLand");
		patient.setEmail("angela.merkel@bundesregierung.de");
		patient.setAddress("Lichtenstein");
		patient.setPhone("+49 89 636 48018");
		patient.setRole(Role.user);
		
		patient.setDateOfBirth(new Date());
		patient.setGender(Gender.FEMALE);
		patient.setBloodType(Blood.A);
		patient.setDoctor(new Doctor());
		patient.setMedicalBackground("Loves to smoke weed.");
		
		patientService.save(patient);
		patient.see();
		return "success";
	}
	
	
//	@RequestMapping(value="/doctor/profile", method = RequestMethod.POST)
//	public String save(@ModelAttribute Doctor doctor) throws ValidationException, SQLException {
//		patient.setId(11226L);
//		patient.setRole(Role.user);
//		patient.setDateOfBirth(new Date());
//		patient.setGender(Gender.FEMALE);
//		patient.setBloodType(Blood.A);
//		patient.setDoctor(new Doctor());
//		
//		patientService.save(patient);
//		patient.see();
//		return "success";
//	}
	
	

	@RequestMapping(method = RequestMethod.GET, params = "action=edit")
	public String edit(@RequestParam("id") Long id) {
		return "appointment_edit";
	}
}
