package org.example.jobapp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Long>> getCompany() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Long company) {
        companyService.createCompany(company);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Long> getCompanyById(@PathVariable java.lang.Long id) {
        var company = companyService.getCompanyById(id);
        if (company == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable java.lang.Long id, @RequestBody Long company) {
        if (companyService.updateCompanyById(id, company)) return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("No such company", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable java.lang.Long id) {
        if (companyService.deleteCompanyById(id)) return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        return new ResponseEntity<>("No such company", HttpStatus.NOT_FOUND);
    }
}
