package com.example.exercises_08_xml.productshop.entities.products;

import java.util.List;

public class ExportSoldProductsDTO {

    private int count;
    private List<ExportNamePriceProductDTO> prducts;

    public ExportSoldProductsDTO(List<ExportNamePriceProductDTO> prducts) {
        this.prducts = prducts;
    }

    public int getCount() {
        return count;
    }

    public List<ExportNamePriceProductDTO> getPrducts() {
        return prducts;
    }
}
