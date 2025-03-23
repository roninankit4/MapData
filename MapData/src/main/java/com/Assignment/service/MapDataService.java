package com.Assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Assignment.Exception.DataNotFoundException;
import com.Assignment.entity.Location;
import com.Assignment.entity.MergedLocation;
import com.Assignment.entity.Metadata;
import com.Assignment.repository.MapDataRepository;

import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MapDataService {

	private static final Logger logger = LoggerFactory.getLogger(MapDataService.class);
    private final MapDataRepository repository;
    private Map<String, MergedLocation> mergedData;

    public MapDataService(MapDataRepository repository) {
        this.repository = repository;
    }
    
    @PostConstruct
	public void initialize() {
        mergedData = mergeData(repository.getLocations(), repository.getMetadata());
    }

    private Map<String, MergedLocation> mergeData(List<Location> locations, List<Metadata> metadata) {
        Map<String, Metadata> metadataMap = metadata.stream().collect(Collectors.toMap(Metadata::getId, m -> m));
        Map<String, MergedLocation> result = new HashMap<>();
        for (Location loc : locations) {
            Metadata meta = metadataMap.get(loc.getId());
            result.put(loc.getId(), new MergedLocation(loc, meta));
        }
        return result;
    }

    public List<MergedLocation> getAllLocations() {
        if (mergedData.isEmpty()) {
            throw new DataNotFoundException("No location data available");
        }
        return new ArrayList<>(mergedData.values());
    }

    public Map<String, Long> getCountPerType() {
        return repository.getMetadata().stream()
                .collect(Collectors.groupingBy(Metadata::getType, Collectors.counting()));
    }

    public Map<String, Double> getAverageRatingPerType() {
        return repository.getMetadata().stream()
                .collect(Collectors.groupingBy(Metadata::getType, Collectors.averagingDouble(Metadata::getRating)));
    }

    public MergedLocation getTopReviewedLocation() {
        return repository.getMetadata().stream()
                .max(Comparator.comparingInt(Metadata::getReviews))
                .map(meta -> mergedData.get(meta.getId()))
                .orElseThrow(() -> new DataNotFoundException("No top-reviewed location found"));
    }

    public List<MergedLocation> getIncompleteData() {
        return mergedData.values().stream()
                .filter(loc -> loc.getMetadata() == null 
                        || Objects.isNull(loc.getMetadata().getRating()) 
                        || Objects.isNull(loc.getMetadata().getReviews()))
                .collect(Collectors.toList());
    }

}