package org.example.jobapp.company;

import java.util.List;

public interface CompanyService {
    List<Long> getAllCompanies();
    Long getCompanyById(java.lang.Long id);
    void createCompany(Long company);
    boolean deleteCompanyById(java.lang.Long id);
    boolean updateCompanyById(java.lang.Long id, Long company);
}
