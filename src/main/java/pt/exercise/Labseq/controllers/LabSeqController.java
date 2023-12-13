package pt.exercise.Labseq.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.exercise.Labseq.services.LabSeqService;

import java.math.BigInteger;

@RestController
@RequestMapping("/labseq")
public class LabSeqController {
    @Autowired
    private LabSeqService labSeqService;

    @GetMapping("/{n}")
    @ApiOperation(value = "Get Lab Sequence Value", notes = "Retrieve the value of the Lab Sequence at a specific index.")
    public ResponseEntity<BigInteger> getLabSeqValue(
            @PathVariable int n
    ) {
        BigInteger result = labSeqService.calculateLabSeq(n);
        return ResponseEntity.ok(result);
    }
}
