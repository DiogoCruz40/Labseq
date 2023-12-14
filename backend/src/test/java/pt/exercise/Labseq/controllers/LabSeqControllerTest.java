package pt.exercise.Labseq.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pt.exercise.Labseq.services.LabSeqService;

import java.math.BigInteger;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LabSeqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LabSeqService labSeqService;

    @InjectMocks
    private LabSeqController labSeqController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(labSeqController).build();
    }
    @Test
    public void testGetLabSeqValue() throws Exception {
        // Mock LabSeqService behavior
        when(labSeqService.calculateLabSeq(anyInt())).thenReturn(BigInteger.TEN);

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/labseq/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("10"));
    }

    @Test
    public void testGetLabSeqValue_CachedValue() throws Exception {
        // Mock LabSeqService behavior with a cached value
        when(labSeqService.calculateLabSeq(anyInt())).thenReturn(BigInteger.TEN);

        // Perform GET request twice to verify caching
        mockMvc.perform(MockMvcRequestBuilders.get("/labseq/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("10"));

        mockMvc.perform(MockMvcRequestBuilders.get("/labseq/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("10"));
    }
    @Test
    public void testGetLabSeqValue_IndexZero() throws Exception {
        // Mock LabSeqService behavior for index 0
        when(labSeqService.calculateLabSeq(0)).thenReturn(BigInteger.ZERO);

        // Perform GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/labseq/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }
    // Add more test cases as needed
}
