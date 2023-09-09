package kz.abylay.example.services.impl;

import kz.abylay.example.models.Company;
import kz.abylay.example.repository.CompanyRepository;
import kz.abylay.example.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }
}
