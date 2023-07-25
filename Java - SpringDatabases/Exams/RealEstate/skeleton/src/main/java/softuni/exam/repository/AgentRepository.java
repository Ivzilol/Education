
package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportBestOffersDto;
import softuni.exam.models.entity.Agent;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByFirstName(String firstName);

    @Query("select new softuni.exam.models.dto.ExportBestOffersDto(" +
            "concat(a.firstName, ' ', a.lastName) as fullName, o.id, o.price, a2.area, t.townName)" +
            " from Agent as a" +
            " join Offer as o on a.id = o.agent.id" +
            " join Apartment as a2 on a2.id = o.apartment.id" +
            " join Town as t on a2.town.id = t.id" +
            " where a2.apartmentType = 'three_rooms'" +
            " order by a2.area desc, o.price")
    List<ExportBestOffersDto> findBestOffers();
}
