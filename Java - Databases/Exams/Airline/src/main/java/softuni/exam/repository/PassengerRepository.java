package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dto.ExportPassengerDto;
import softuni.exam.models.entity.Passenger;

import java.util.List;
import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findByEmail(String email);

    @Query("select new softuni.exam.models.dto.ExportPassengerDto(" +
            " p.firstName, p.lastName, p.email, p.phoneNumber, t.serialNumber, count(t.passenger) as countTickets)" +
            " from Passenger as p" +
            " join Ticket as t on p.id = t.passenger.id" +
            " group by p.email" +
            " order by countTickets desc, p.email")
    List<ExportPassengerDto> exportPassenger();
}
