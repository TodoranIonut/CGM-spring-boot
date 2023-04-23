package com.CGMspringboot.controller;

import com.CGMspringboot.controller.dto.request.DoctorRequestDTO;
import com.CGMspringboot.controller.dto.response.DoctorResponseDTO;
import com.CGMspringboot.controller.mappers.DoctorMapper;
import com.CGMspringboot.domain.entity.Doctor;
import com.CGMspringboot.exceptions.CGMApplicationException;
import com.CGMspringboot.exceptions.appUser.UserEmailNotFoundException;
import com.CGMspringboot.exceptions.appUser.UserIdNotFoundException;
import com.CGMspringboot.service.doctorService.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @GetMapping("/id/{id}")
    public ResponseEntity<DoctorResponseDTO> getAppUserById(@PathVariable Integer id) throws UserIdNotFoundException {
        Doctor doctor = doctorService.findDoctorById(id);
        DoctorResponseDTO doctorResponseDTO = doctorMapper.toDoctorResponseDTO(doctor);
        return ResponseEntity.ok().body(doctorResponseDTO);
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<DoctorResponseDTO> getAppUserById(@PathVariable String email) throws UserEmailNotFoundException {
        Doctor doctor = doctorService.findDoctorByEmail(email);
        DoctorResponseDTO doctorResponseDTO = doctorMapper.toDoctorResponseDTO(doctor);
        return ResponseEntity.ok().body(doctorResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<DoctorResponseDTO> registerDoctor(@RequestBody DoctorRequestDTO doctorRequestDTO) throws CGMApplicationException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/doctor/register").toUriString());
        Doctor doctor = doctorMapper.toDoctor(doctorRequestDTO);
        Doctor registeredDoctor = doctorService.registerNewDoctor(doctor);
        DoctorResponseDTO doctorResponseDTO = doctorMapper.toDoctorResponseDTO(registeredDoctor);
        return ResponseEntity.created(uri).body(doctorResponseDTO);
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<DoctorResponseDTO> updateAppUser(@PathVariable Integer id, @RequestBody DoctorRequestDTO doctorRequestDTO) throws CGMApplicationException {
        Doctor doctor = doctorMapper.toDoctor(doctorRequestDTO);
        Doctor updatedDoctor = doctorService.updateDoctor(id,doctor);
        DoctorResponseDTO doctorResponseDTO = doctorMapper.toDoctorResponseDTO(updatedDoctor);
        return ResponseEntity.ok().body(doctorResponseDTO);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable Integer id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.ok().body("delete user id " + id);
    }
}
