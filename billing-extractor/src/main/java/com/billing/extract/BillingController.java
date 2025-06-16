package com.billing.extract;

import com.billing.extract.model.BillingInfo;
import com.billing.extract.service.BillingExtractorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingExtractorService service;

    public BillingController(BillingExtractorService service) {
        this.service = service;
    }

    @PostMapping(path = "/extract", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BillingInfo extract(@RequestParam("file") MultipartFile file,
                               @RequestParam("prompt") String prompt) throws IOException {
        return service.extract(file, prompt);
    }
}
