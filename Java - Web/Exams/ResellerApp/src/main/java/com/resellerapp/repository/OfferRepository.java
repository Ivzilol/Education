package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Set<Offer> findOfferById(Long id);
}
