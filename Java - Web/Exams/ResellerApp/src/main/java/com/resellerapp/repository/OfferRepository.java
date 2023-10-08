package com.resellerapp.repository;

import com.resellerapp.model.entity.dto.BoughtOfferDTO;
import com.resellerapp.model.entity.dto.OfferOtherUserDTO;
import com.resellerapp.model.entity.dto.OffersCurrentUserDTO;
import com.resellerapp.model.entity.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("select new com.resellerapp.model.entity.dto.OffersCurrentUserDTO(" +
            " o.id, c.conditionName, o.price, o.description as description)" +
            " from Offer as o" +
            " join Condition as c on o.condition.id = c.id" +
            " where o.user.id = :id")
    Set<OffersCurrentUserDTO> findOffersCurrentUser(Long id);

    @Query("select new com.resellerapp.model.entity.dto.OfferOtherUserDTO(" +
            "o.id, u.username, o.description, c.conditionName as conditionName, o.price)" +
            " from Offer as o" +
            " join Condition as c on o.condition.id = c.id" +
            " join User as u on o.user.id = u.id" +
            " where o.user.id <> :id and o.status = 'newOffer'")
    Set<OfferOtherUserDTO> findOfferOtherUsers(Long id);

    @Query("select o from Offer as o" +
            " where o.user.id <> :id")
    Set<Offer> findBoughtOfferUser(Long id);
}
