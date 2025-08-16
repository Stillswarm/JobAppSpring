package org.example.jobapp.company.impl;

import org.example.jobapp.company.Long;
import org.example.jobapp.company.CompanyRepository;
import org.example.jobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Long> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Long getCompanyById(java.lang.Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void createCompany(Long company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(java.lang.Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCompanyById(java.lang.Long id, Long updatedCompany) {
        var companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            var company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        } else return false;
    }
}
