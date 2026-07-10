package com.guptatyre.service;


import com.guptatyre.entity.Brand;
import com.guptatyre.repository.BrandRepository;
import com.guptatyre.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public Brand getById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Brand not found."));

    }

    @Override
    public Brand save(Brand brand) {

        Optional<Brand> existing =
                repository.findByName(brand.getName());

        if (existing.isPresent()
                && !existing.get().getId().equals(brand.getId())) {

            throw new RuntimeException("Brand already exists.");

        }

        if (brand.getId() == null) {

            return repository.save(brand);

        }

        Brand dbBrand = getById(brand.getId());

        dbBrand.setName(brand.getName());
        dbBrand.setDescription(brand.getDescription());
        dbBrand.setActive(brand.getActive());

        return repository.save(dbBrand);

    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);

    }

}