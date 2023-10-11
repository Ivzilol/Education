package com.resellerapp.service;

import com.resellerapp.model.dto.AddOfferDTO;
import com.resellerapp.model.dto.BoughtOfferCurrentUser;
import com.resellerapp.model.dto.CurrentUserOffersDTO;
import com.resellerapp.model.dto.OtherUsersOffersDTO;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.ConditionRepository;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferService {

    private final ConditionRepository conditionRepository;

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    public OfferService(ConditionRepository conditionRepository, OfferRepository offerRepository, UserRepository userRepository) {
        this.conditionRepository = conditionRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    public void addOffer(AddOfferDTO addOfferDTO, Long userId) {
        Offer offer = new Offer();
        offer.setDescription(addOfferDTO.getDescription());
        offer.setPrice(addOfferDTO.getPrice());
        Condition condition = this.conditionRepository.findCondition(addOfferDTO.getCondition());
        offer.setCondition(condition);
        Optional<User> user = this.userRepository.findById(userId);
        Set<Offer> userOffer = new HashSet<>(user.get().getOffers());
        userOffer.add(offer);
        user.get().setOffers(userOffer);
        this.offerRepository.save(offer);
        this.userRepository.save(user.get());
    }

    public Set<CurrentUserOffersDTO> getOffersCurrentUser(Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        Set<Offer> offersCurrentUser = new HashSet<>(user.get().getOffers());
        Set<CurrentUserOffersDTO> currentUserOffersDTOS = new HashSet<>();
        for (Offer currentOffer : offersCurrentUser) {
            CurrentUserOffersDTO currentUserOffersDTO = new CurrentUserOffersDTO();
            currentUserOffersDTO.setId(currentOffer.getId());
            currentUserOffersDTO.setCondition(currentOffer.getCondition());
            currentUserOffersDTO.setPrice(currentOffer.getPrice());
            currentUserOffersDTO.setDescription(currentOffer.getDescription());
            currentUserOffersDTOS.add(currentUserOffersDTO);
        }
        return currentUserOffersDTOS;
    }

    public Set<OtherUsersOffersDTO> getOtherUsersOffers(Long userId) {
        List<Offer> allOffers = this.offerRepository.findAll();
        Optional<User> user = this.userRepository.findById(userId);
        Set<OtherUsersOffersDTO> otherUsersOffersDTOS = new HashSet<>();
        findOtherOffers(allOffers, user, otherUsersOffersDTOS);
        return otherUsersOffersDTOS;
    }

    private void findOtherOffers(List<Offer> allOffers, Optional<User> user, Set<OtherUsersOffersDTO> otherUsersOffersDTOS) {
        for (Offer currentOffer : allOffers) {
            boolean isContain = false;
            for (Offer userOffer : user.get().getOffers()) {
                if (Objects.equals(currentOffer.getId(), userOffer.getId())) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                OtherUsersOffersDTO otherUsersOffersDTO = new OtherUsersOffersDTO();
                otherUsersOffersDTO.setId(currentOffer.getId());
                otherUsersOffersDTO.setPrice(currentOffer.getPrice());
                otherUsersOffersDTO.setDescription(currentOffer.getDescription());
                otherUsersOffersDTO.setCondition(currentOffer.getCondition());
                User currentUser = this.userRepository.findByOffersId(currentOffer.getId());
                if (currentUser == null) {
                    continue;
                }
                otherUsersOffersDTO.setUsername(currentUser.getUsername());
                otherUsersOffersDTOS.add(otherUsersOffersDTO);
            }
        }
    }

    public void boughtOffer(Long id, Long userId) {
        Set<Offer> boughtOffer = this.offerRepository.findOfferById(id);
        Optional<User> userBoughtOffer = this.userRepository.findById(userId);
        Set<Offer> offersCurrentUser = new HashSet<>(userBoughtOffer.get().getBoughtOffers());
        offersCurrentUser.addAll(boughtOffer);
        userBoughtOffer.get().setBoughtOffers(offersCurrentUser);
        userRepository.save(userBoughtOffer.get());
        User seller = sellerRemoveOffer(id);
        this.userRepository.save(seller);
    }

    private User sellerRemoveOffer(Long id) {
        User seller = this.userRepository.findByOffersId(id);
        Set<Offer> newListSeller = new HashSet<>();
        for (Offer currentOffer : seller.getOffers()) {
            if (!Objects.equals(currentOffer.getId(), id)) {
                newListSeller.add(currentOffer);
            }
        }
        seller.setOffers(newListSeller);
        return seller;
    }

    public Set<BoughtOfferCurrentUser> getBoughtOfferCurrentUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        Set<BoughtOfferCurrentUser> boughtOffer = new HashSet<>();
        for (Offer offer : user.get().getBoughtOffers()) {
            BoughtOfferCurrentUser boughtOfferCurrentUser = new BoughtOfferCurrentUser();
            boughtOfferCurrentUser.setDescription(offer.getDescription());
            boughtOfferCurrentUser.setPrice(offer.getPrice());
            boughtOffer.add(boughtOfferCurrentUser);
        }
        return boughtOffer;
    }

    public void deleteOffer(Long id, Long userId) {
        Optional<User> seller = this.userRepository.findById(userId);
        Set<Offer> newListSeller = new HashSet<>();
        for (Offer offer : seller.get().getOffers()) {
            if (!Objects.equals(offer.getId(), id)) {
                newListSeller.add(offer);
            }
        }
        seller.get().setOffers(newListSeller);
        this.userRepository.save(seller.get());
        Optional<Offer> offerForRemove = this.offerRepository.findById(id);
        this.offerRepository.delete(offerForRemove.get());
    }
}
