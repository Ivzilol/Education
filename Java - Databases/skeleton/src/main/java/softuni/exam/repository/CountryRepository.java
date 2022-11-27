package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Country;

import java.util.Optional;


public interface CountryRepository extends JpaRepository<Country, Long> {


    Optional<Country> findByCountryName(String countryName);


    Optional<Country> getCountryById(long country);
}
