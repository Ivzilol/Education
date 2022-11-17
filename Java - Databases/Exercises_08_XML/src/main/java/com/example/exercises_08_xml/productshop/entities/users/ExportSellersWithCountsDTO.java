package com.example.exercises_08_xml.productshop.entities.users;


import java.util.List;

public class ExportSellersWithCountsDTO {
    private int usersCount;
    private List<ExportUserWithSoldCountDTO> users;

    public ExportSellersWithCountsDTO() {
    }

    public ExportSellersWithCountsDTO(
            List<ExportUserWithSoldCountDTO> users) {
        this.users = users;
        this.usersCount = this.users.size();
    }
}
