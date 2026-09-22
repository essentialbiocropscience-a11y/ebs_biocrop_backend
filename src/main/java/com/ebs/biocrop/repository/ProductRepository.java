package com.ebs.biocrop.repository;

import com.ebs.biocrop.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByVariationCode(String variationCode);

    List<Product> findByProductCode(String productCode);

    List<Product> findByCategory(String category);

    boolean existsByVariationCode(String variationCode);
}
