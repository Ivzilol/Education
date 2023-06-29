package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Offer;
import softuni.exam.models.enums.ApartmentType;

import java.util.List;
import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o.id, o.price, a.firstName, a.lastName, ap.area, t.townName from Offer as o" +
            " join Agent as a on o.agent.id = a.id" +
            " join Apartment as ap on o.apartment.id = ap.id" +
            " join Town as t on ap.town.id = t.id" +
            " where ap.apartmentType = 'three_rooms'" +
            " order by ap.area desc, o.price")
    List<Offer> findWithThreeRooms();

    List<Offer> findAllByApartment_ApartmentTypeOrderByApartment_AreaDescPriceAsc(ApartmentType three_rooms);
}
