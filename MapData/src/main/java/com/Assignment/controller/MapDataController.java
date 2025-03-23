package com.Assignment.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.entity.MergedLocation;
import com.Assignment.service.MapDataService;


@RestController
@RequestMapping("/api")
public class MapDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(MapDataController.class);
    private final MapDataService service;

    public MapDataController(MapDataService service) {
        this.service = service;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<MergedLocation>> getAllLocations() {
        return ResponseEntity.ok(service.getAllLocations());
    }

    @GetMapping("/stats/count-per-type")
    public ResponseEntity<Map<String, Long>> getCountPerType() {
        return ResponseEntity.ok(service.getCountPerType());
    }

    @GetMapping("/stats/avg-rating-per-type")
    public ResponseEntity<Map<String, Double>> getAverageRatingPerType() {
        return ResponseEntity.ok(service.getAverageRatingPerType());
    }

    @GetMapping("/stats/top-reviewed-location")
    public ResponseEntity<MergedLocation> getTopReviewedLocation() {
        return ResponseEntity.ok(service.getTopReviewedLocation());
    }

    @GetMapping("/stats/incomplete-data")
    public ResponseEntity<List<MergedLocation>> getIncompleteData() {
        return ResponseEntity.ok(service.getIncompleteData());
    }

}
