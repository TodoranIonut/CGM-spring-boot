package com.CGMspringboot.controller;

import com.CGMspringboot.controller.dto.request.PatientRequestDTO;
import com.CGMspringboot.controller.dto.response.PatientResponseDTO;
import com.CGMspringboot.controller.mappers.PatientMapper;
import com.CGMspringboot.domain.entity.Patient;
import com.CGMspringboot.exceptions.CGMApplicationException;
import com.CGMspringboot.exceptions.appUser.UserEmailNotFoundException;
import com.CGMspringboot.exceptions.appUser.UserIdNotFoundException;
import com.CGMspringboot.service.patientService.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @GetMapping("/id/{id}")
    public ResponseEntity<PatientResponseDTO> getAppUserById(@PathVariable Integer id) throws UserIdNotFoundException {
        Patient patient = patientService.findPatientById(id);
        PatientResponseDTO patientResponseDTO = patientMapper.toPatientResponseDTO(patient);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<PatientResponseDTO> getAppUserById(@PathVariable String email) throws UserEmailNotFoundException {
        Patient patient = patientService.findPatientByEmail(email);
        PatientResponseDTO patientResponseDTO = patientMapper.toPatientResponseDTO(patient);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<PatientResponseDTO> registerPatient(@RequestBody PatientRequestDTO patientRequestDTO) throws CGMApplicationException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/patient/register").toUriString());
        Patient patient = patientMapper.toPatient(patientRequestDTO);
        Patient registeredPatient = patientService.registerNewPatient(patient);
        PatientResponseDTO patientResponseDTO = patientMapper.toPatientResponseDTO(registeredPatient);
        return ResponseEntity.created(uri).body(patientResponseDTO);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<PatientResponseDTO> updateAppUser(@PathVariable Integer id, @RequestBody PatientRequestDTO patientRequestDTO) throws CGMApplicationException {
        Patient patient = patientMapper.toPatient(patientRequestDTO);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        PatientResponseDTO patientResponseDTO = patientMapper.toPatientResponseDTO(updatedPatient);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable Integer id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok().body("delete user id " + id);
    }
}
