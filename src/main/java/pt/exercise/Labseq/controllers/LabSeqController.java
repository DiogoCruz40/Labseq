package pt.exercise.Labseq.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.exercise.Labseq.services.LabSeqService;

import java.math.BigInteger;

@Tag(name = "Labseq Sequence API", description = "Operations pertaining to Labseq Sequence")
@RestController
@RequestMapping("/labseq")
public class LabSeqController {
    @Autowired
    private LabSeqService labSeqService;

    @Operation(summary = "Retrieve the value of the Labseq Sequence at a specific index",
            parameters = {
                    @Parameter(name = "n", in = ParameterIn.PATH,
                            description = "Index of the Labseq Sequence", required = true, schema = @Schema(type = "integer", defaultValue = "0"))},
    responses = {
            // in case of 200 returns the value of the Lab Sequence at the specified index
            @ApiResponse(responseCode = "200", description = "Value of the Labseq Sequence at the specified index", content = @Content(schema = @Schema(implementation = String.class))),
            // in case of 400 returns the error message
            @ApiResponse(responseCode = "400", description = "Invalid index", content = @Content(schema = @Schema(implementation = String.class))),
            // in case of 500 returns the error message
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = String.class)))
    }    )
    @GetMapping("/{n}")
    public ResponseEntity<String> getLabSeqValue(
            @PathVariable int n
    ) {
        return ResponseEntity.ok(labSeqService.calculateLabSeq(n) + "");
    }
}
