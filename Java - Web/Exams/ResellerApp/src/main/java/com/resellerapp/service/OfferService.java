package com.resellerapp.service;

import com.resellerapp.model.entity.dto.AddOfferDTO;
import com.resellerapp.model.entity.dto.BoughtOfferDTO;
import com.resellerapp.model.entity.dto.OfferOtherUserDTO;
import com.resellerapp.model.entity.dto.OffersCurrentUserDTO;
import com.resellerapp.model.entity.entity.Condition;
import com.resellerapp.model.entity.entity.Offer;
import com.resellerapp.model.entity.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    private final ConditionRepository conditionRepository;

    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, ConditionRepository conditionRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;
        this.userRepository = userRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = new Offer();
        offer.setDescription(addOfferDTO.getDescription());
        offer.setPrice(addOfferDTO.getPrice());
        Condition condition = this.conditionRepository.findCondition(addOfferDTO.getCondition());
        offer.setCondition(condition);
        Optional<User> user = this.userRepository.findById(addOfferDTO.getId());
        offer.setUser(user.get());
        offer.setStatus("newOffer");
        this.offerRepository.save(offer);
    }

    public Set<OffersCurrentUserDTO> findAllOffersCurrentUser(Long id) {
        return this.offerRepository.findOffersCurrentUser(id);
    }

    public void removeOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

    public Set<OfferOtherUserDTO> findOfferOtherUsers(Long id) {
        return this.offerRepository.findOfferOtherUsers(id);
    }

    public void boughtOffer(Long id, Long loggedUserId) {
        Optional<Offer>offer = this.offerRepository.findById(id);
        Optional<User> user = this.userRepository.findById(loggedUserId);
        offer.get().getBoughtOffers().add(user.get());
        offer.get().setBoughtOffers(offer.get().getBoughtOffers());
        offer.get().setStatus("bought");
        this.offerRepository.save(offer.get());
    }

    public Set<BoughtOfferDTO> findBoughtOfferCurrentUser(Long id) {
        Set<Offer> offerCurrentUser = this.offerRepository.findBoughtOfferUser(id);
        Set<BoughtOfferDTO> boughtOffer = new HashSet<>();
        for (Offer currentOffer : offerCurrentUser) {
            currentOffer.getBoughtOffers().forEach(b -> {
                if (Objects.equals(b.getId(), id)) {
                    BoughtOfferDTO boughtOfferDTO = new BoughtOfferDTO();
                    boughtOfferDTO.setId(b.getId());
                    boughtOfferDTO.setDescription(currentOffer.getDescription());
                    boughtOfferDTO.setPrice(currentOffer.getPrice());
                    boughtOffer.add(boughtOfferDTO);
                }
            });
        }

       return boughtOffer;
    }
}
