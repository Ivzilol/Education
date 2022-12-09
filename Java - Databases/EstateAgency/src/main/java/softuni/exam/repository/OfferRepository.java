package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {


    Optional<Offer> findByPrice(BigDecimal price);

    List<Offer> findAllByApartments_ApartmentTypeOrderByApartments_AreaDescPriceAsc(ApartmentType apartment_apartmentType);
}
