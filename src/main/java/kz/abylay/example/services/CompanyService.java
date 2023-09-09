package kz.abylay.example.services;

import kz.abylay.example.models.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();

    Company getCompanyById(Integer id);

}
