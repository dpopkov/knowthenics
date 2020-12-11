package ru.dpopkov.knowthenics.services;

import ru.dpopkov.knowthenics.model.Company;

import java.util.Set;

public interface CompanyService {

    Set<Company> findAll();

    Company findById(Long id);

    void save(Company company);

    void delete(Company company);

    void deleteById(Long id);
}
