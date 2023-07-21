package hiberspring.service.impl;

import hiberspring.models.dtos.ProductSeedRootDto;
import hiberspring.models.entities.Branch;
import hiberspring.models.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final BranchRepository branchRepository;
    private static final String PRODUCT_PATH = "src/main/resources/files/products.xml";

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files
                .readString(Path.of(PRODUCT_PATH));
    }

    @Override
    public String importProducts() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser
                .fromFile(PRODUCT_PATH, ProductSeedRootDto.class)
                .getProductSeedDtoList()
                .stream()
                .filter(productSeedDto -> {
                    boolean isValid = validationUtil.isValid(productSeedDto);

                    Optional<Branch> branchByName = this.branchRepository
                            .findByName(productSeedDto.getBranch());

                    if (branchByName.isEmpty()) {
                        isValid = false;
                    }

                    sb.append(isValid
                    ?
                            String.format("Successfully imported Product %s",
                                    productSeedDto.getName())
                            :
                            "Error: Invalid data."
                    ).append(System.lineSeparator());
                    return isValid;
                }).map(productSeedDto -> {
                    Product product = mapper.map(productSeedDto, Product.class);
                    Optional<Branch> branchByName = this.branchRepository
                            .findByName(productSeedDto.getBranch());
                    product.setBranch(branchByName.get());
                    return product;
                })
                .forEach(this.productRepository::save);
        return sb.toString();
    }
}
