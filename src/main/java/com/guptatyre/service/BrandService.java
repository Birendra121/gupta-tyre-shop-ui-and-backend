package com.guptatyre.service;

import com.guptatyre.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();

    Brand getById(Long id);

    Brand save(Brand brand);

    void delete(Long id);


}
