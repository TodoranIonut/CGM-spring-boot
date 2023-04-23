package com.CGMspringboot.controller;

import com.CGMspringboot.controller.dto.request.GlucoseLevelRequestDTO;
import com.CGMspringboot.controller.dto.response.GlucoseLevelResponseDTO;
import com.CGMspringboot.controller.mappers.GlucoseLevelMapper;
import com.CGMspringboot.domain.entity.GlucoseLevel;
import com.CGMspringboot.exceptions.CGMApplicationException;
import com.CGMspringboot.exceptions.appUser.UserIdNotFoundException;
import com.CGMspringboot.exceptions.date.InvalidDateFormatException;
import com.CGMspringboot.service.glucoseLevel.GlucoseLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/glucose")
public class GlucoseLevelController {

    private final GlucoseLevelService glucoseLevelService;
    private final GlucoseLevelMapper glucoseLevelMapper;

    @PostMapping("/save")
    public ResponseEntity<GlucoseLevelResponseDTO> saveGlucoseLevel(@RequestBody GlucoseLevelRequestDTO glucoseLevelRequestDTO) throws UserIdNotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/glucose/save").toUriString());
        GlucoseLevel glucoseLevel = glucoseLevelMapper.toGlucoseLevel(glucoseLevelRequestDTO);
        GlucoseLevel savedGlucoseLevel = glucoseLevelService.saveGlucoseLevel(glucoseLevel);
        GlucoseLevelResponseDTO glucoseLevelResponseDTO = glucoseLevelMapper.toGlucoseLevelResponseDTO(savedGlucoseLevel);
        return ResponseEntity.created(uri).body(glucoseLevelResponseDTO);
    }

    @GetMapping("/byDate/patient/id/{id}")
    public ResponseEntity<List<GlucoseLevelResponseDTO>> getDailyGlucoseLevel(@PathVariable Integer id, @RequestParam String dateFormat, @RequestParam  String date) throws CGMApplicationException {
        SimpleDateFormat simpleDateFormat = null;
        Date formatedDate = null;
        try{
            simpleDateFormat = new SimpleDateFormat(dateFormat);
            formatedDate = simpleDateFormat.parse(date);
        } catch (IllegalArgumentException | ParseException e){
           throw new InvalidDateFormatException();
        }
        List<GlucoseLevel> dalyGlucoseLevel = glucoseLevelService.getGlucoseLevelByDate(formatedDate,id);
        List<GlucoseLevelResponseDTO> responseList = glucoseLevelMapper.toGlucoseLevelResponseDTOList(dalyGlucoseLevel);
        return ResponseEntity.ok().body(responseList);
    }

}
