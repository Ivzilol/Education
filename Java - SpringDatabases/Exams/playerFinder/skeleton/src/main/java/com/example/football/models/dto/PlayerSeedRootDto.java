package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedRootDto {

    @XmlElement(name = "player")
    private List<PlayerSeedDto> playerSeedDtoList;

    public List<PlayerSeedDto> getPlayerSeedDtoList() {
        return playerSeedDtoList;
    }

    public void setPlayerSeedDtoList(List<PlayerSeedDto> playerSeedDtoList) {
        this.playerSeedDtoList = playerSeedDtoList;
    }
}
