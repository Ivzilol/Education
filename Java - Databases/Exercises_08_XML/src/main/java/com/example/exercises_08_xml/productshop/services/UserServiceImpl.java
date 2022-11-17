package com.example.exercises_08_xml.productshop.services;

import com.example.exercises_08_xml.productshop.entities.products.ExportNamePriceProductDTO;
import com.example.exercises_08_xml.productshop.entities.products.ExportSoldProductsDTO;
import com.example.exercises_08_xml.productshop.entities.users.*;
import com.example.exercises_08_xml.productshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();
        List<ExportUserWithSoldProductsDTO> dtos =
                users
                        .stream()
                        .map(u -> this.mapper.map(u, ExportUserWithSoldProductsDTO.class))
                        .collect(Collectors.toList());
        return new ExportSellersDTO(dtos);
    }

    @Override
    @Transactional
    public ExportSellersWithCountsDTO findAllWithSoldProductsAndCounts() {
        List<User> users = this.userRepository.findAllWithSoldProductsOrderByCount();

        List<ExportUserWithSoldCountDTO> dtos =
                users
                        .stream()
                        .map(this::createExportUserWithSoldCountDTO)
                        .collect(Collectors.toList());
        return new ExportSellersWithCountsDTO(dtos);
    }

    private ExportUserWithSoldCountDTO createExportUserWithSoldCountDTO(User u) {
        ExportUserWithSoldCountDTO userDto =
                this.mapper.map(u, ExportUserWithSoldCountDTO.class);
        List<ExportNamePriceProductDTO> namePriceProductDTOs = u.getSellingItems()
                .stream()
                .map(p -> this.mapper.map(p, ExportNamePriceProductDTO.class))
                .collect(Collectors.toList());
        ExportSoldProductsDTO soldProductsDTO = new ExportSoldProductsDTO(namePriceProductDTOs);

        userDto.setSoldProducts(soldProductsDTO);
        return userDto;
    }
}
