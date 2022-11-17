package com.example.exercises_08_xml.productshop.services;

import com.example.exercises_08_xml.productshop.entities.products.ExportProductsInRangeDTO;
import com.example.exercises_08_xml.productshop.entities.products.Product;
import com.example.exercises_08_xml.productshop.entities.products.ProductWithAttributesDTO;
import com.example.exercises_08_xml.productshop.entities.users.User;
import com.example.exercises_08_xml.productshop.repositories.ProductRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private final TypeMap<Product, ProductWithAttributesDTO> productDoDtoMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();
        Converter<User, String> userToFullNameConverter =
                        Context -> Context.getSource() == null ? null :
                        Context.getSource().getFullName();
        TypeMap<Product, ProductWithAttributesDTO> typeMap =
                this.mapper.createTypeMap(Product.class, ProductWithAttributesDTO.class);
        this.productDoDtoMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                        .map(Product::getSeller, ProductWithAttributesDTO::setSeller));


    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf(from);
        BigDecimal rangeTo = BigDecimal.valueOf(to);
        List<Product> products = this.productRepository
                .findAllByPriceBetweenAndBayerIsNullOrderByPriceDesc(rangeFrom, rangeTo);
        List<ProductWithAttributesDTO> dtos =
                products
                        .stream()
                        .map(this.productDoDtoMapping::map)
                        .collect(Collectors.toList());
        return new ExportProductsInRangeDTO(dtos);
    }
}
