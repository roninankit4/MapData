package com.Assignment;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Assignment.entity.Location;
import com.Assignment.entity.MergedLocation;
import com.Assignment.entity.Metadata;
import com.Assignment.service.MapDataService;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MapDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MapDataService service;

    @Test
    void testGetAllLocations() throws Exception {
        List<MergedLocation> mergedLocations = List.of(new MergedLocation(
                new Location("1", 12.34, 56.78),
                new Metadata("1", "Hotel", 4.5, 200)
        ));

        when(service.getAllLocations()).thenReturn(mergedLocations);

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].metadata.type").value("Hotel"));
    }

    @Test
    void testGetCountPerType() throws Exception {
        when(service.getCountPerType()).thenReturn(Map.of("Hotel", 1L));

        mockMvc.perform(get("/api/stats/count-per-type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Hotel").value(1));
    }

    @Test
    void testGetAverageRatingPerType() throws Exception {
        when(service.getAverageRatingPerType()).thenReturn(Map.of("Hotel", 4.5));

        mockMvc.perform(get("/api/stats/avg-rating-per-type"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Hotel").value(4.5));
    }
}
