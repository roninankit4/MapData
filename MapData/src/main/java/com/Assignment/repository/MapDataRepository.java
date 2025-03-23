package com.Assignment.repository;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Assignment.Exception.DataProcessingException;
import com.Assignment.entity.Location;
import com.Assignment.entity.Metadata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Repository
public class MapDataRepository {
	
	 private static final Logger logger = LoggerFactory.getLogger(MapDataRepository.class);
	    private List<Location> locations = new ArrayList<>();
	    private List<Metadata> metadata = new ArrayList<>();
	    
	    public MapDataRepository() {
	        loadData();
	    }
	    
	    @PostConstruct
	    private void loadData() {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            locations = mapper.readValue(Files.readAllBytes(Paths.get("locations.json")), new TypeReference<>() {});
	            metadata = mapper.readValue(Files.readAllBytes(Paths.get("metadata.json")), new TypeReference<>() {});
	        } catch (IOException e) {
	            logger.error("Error loading JSON data", e);
	            throw new DataProcessingException("Failed to load data", e);
	        }
	    }
	    
	    public List<Location> getLocations() { return locations; }
	    public List<Metadata> getMetadata() { return metadata; }

}
