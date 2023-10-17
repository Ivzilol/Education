package com.example.BattleShips.validation;

import com.example.BattleShips.service.ShipService;
import com.example.BattleShips.validation.annotation.UniqueShipName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueShipNameValidator implements ConstraintValidator<UniqueShipName, String> {

    private final ShipService shipService;

    public UniqueShipNameValidator(ShipService shipService) {
        this.shipService = shipService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.shipService.findShipByName(value).isEmpty();
    }
}
