package com.example.mobile.service;

import com.example.mobile.model.dto.AddOfferDTO;

import com.example.mobile.model.dto.OfferDetailDTO;
import com.example.mobile.model.dto.SearchOfferDTO;
import com.example.mobile.model.entity.ModelEntity;
import com.example.mobile.model.entity.OfferEntity;
import com.example.mobile.model.entity.UserEntity;
import com.example.mobile.model.enums.UserRoleEnum;
import com.example.mobile.model.mapper.OfferMapper;
import com.example.mobile.repository.ModelRepository;
import com.example.mobile.repository.OfferRepository;
import com.example.mobile.repository.OfferSpecification;
import com.example.mobile.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    public boolean isOwner(String userName, UUID offerId) {
        boolean isOwner = offerRepository.findById(offerId)
                .filter(offer -> offer.getSeller()
                        .getEmail().equals(userName))
                .isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository
                .findByEmail(userName)
                .filter(this::isAdmin)
                .isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles()
                .stream()
                .anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void deleteOfferById(UUID offerId) {
        offerRepository.deleteById(offerId);
    }

    public Page<OfferEntity> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper
                .addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).orElseThrow();
        newOffer.setModel(model);
        newOffer.setSeller(seller);
        offerRepository.save(newOffer);
    }

    public Optional<OfferDetailDTO> findOfferByUUID(UUID offerId) {
        return offerRepository
                .findById(offerId)
                .map(offerMapper::offerEntityToOfferDetailDto);
    }

    public List<OfferDetailDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO))
                .stream()
                .map(offerMapper::offerEntityToOfferDetailDto)
                .toList();
    }

    public Optional<AddOfferDTO> getOfferEditDetails(UUID offerId) {
        return offerRepository
                .findById(offerId)
                .map(offerMapper::offerEntityToCreateOrUpdateOfferDtoTo);
    }

}
