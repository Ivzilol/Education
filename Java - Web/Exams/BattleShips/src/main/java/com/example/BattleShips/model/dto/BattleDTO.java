package com.example.BattleShips.model.dto;

public class BattleDTO {

    private Long userShipId;

    private Long enemyShipId;

    public Long getUserShipId() {
        return userShipId;
    }

    public void setUserShipId(Long userShipId) {
        this.userShipId = userShipId;
    }

    public Long getEnemyShipId() {
        return enemyShipId;
    }

    public void setEnemyShipId(Long enemyShipId) {
        this.enemyShipId = enemyShipId;
    }
}
