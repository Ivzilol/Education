package football.core;


import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static football.common.ConstantMessages.*;
import static football.common.ExceptionMessages.INVALID_PLAYER_TYPE;

public class ControllerImpl implements Controller {

    private final SupplementRepositoryImpl supplementRepository;

    private final Collection<Field> fields;


    public ControllerImpl() {

        this.supplementRepository = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();

    }

    @Override
    public String addField(String fieldType, String fieldName) {
        if (!(fieldType.equals("ArtificialTurf") || fieldType.equals("NaturalGrass"))) {
            String exceptionMessage = ExceptionMessages.INVALID_FIELD_TYPE;
            throw new NullPointerException(exceptionMessage);
        } else {
            switch (fieldType) {
                case "ArtificialTurf":
                    Field fieldTurf = new ArtificialTurf(fieldName);
                    this.fields.add(fieldTurf);
                    break;
                case "NaturalGrass":
                    Field fieldGrass = new NaturalGrass(fieldName);
                    this.fields.add(fieldGrass);
                    break;
            }

            return String.format(SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType);
        }
    }

    @Override
    public String deliverySupplement(String type) {
        if (!(type.equals("Powdered") || type.equals("Liquid"))) {
            String exceptionMessage = ExceptionMessages.INVALID_SUPPLEMENT_TYPE;
            throw new IllegalArgumentException(exceptionMessage);
        } else {
            Supplement supplement;
            switch (type) {
                case "Powdered":
                    supplement = new Powdered();
                    supplementRepository.add(supplement);
                    break;
                case "Liquid":
                    supplement = new Liquid();
                    supplementRepository.add(supplement);
                    break;
            }
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
        }
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = getFieldByName(fieldName);
        Supplement supplement = supplementRepository.findByType(supplementType);
        if (supplement == null) {
            String exceptionMessage = String.format(ExceptionMessages.
                    NO_SUPPLEMENT_FOUND, supplementType);
            throw new IllegalArgumentException(exceptionMessage);
        } else {
            field.addSupplement(supplement);
            return String.format(ConstantMessages.
                    SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
        }
    }


    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field field = getFieldByName(fieldName);
        Player player;
        switch (playerType) {
            case "Women":
                player = new Women(playerName, nationality, strength);
                break;
            case "Men":
                player = new Men(playerName, nationality, strength);
                break;
            default:
                throw new IllegalArgumentException(INVALID_PLAYER_TYPE);
        }
        if (field.getClass().getSimpleName().equals("ArtificialTurf") && playerType.equals("Women")) {
            field.addPlayer(player);
        } else if (field.getClass().getSimpleName().equals("NaturalGrass") && playerType.equals("Men")) {
            field.addPlayer(player);
        } else {
            return FIELD_NOT_SUITABLE;
        }
        return String.format(ConstantMessages.
                SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = getFieldByName(fieldName);
        for (Player player : field.getPlayers()) {
            player.stimulation();
        }
        return String.format(PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = getFieldByName(fieldName);
        int sumStrength = 0;
        for (Player player : field.getPlayers()) {
            sumStrength += player.getStrength();
        }

        return String.format(STRENGTH_FIELD, fieldName, sumStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : this.fields) {
            stringBuilder.append(field.getInfo());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private Field getFieldByName(String fieldName) {
        return fields.stream()
                .filter(f -> f.getName().equals(fieldName))
                .findFirst()
                .orElse(null);
    }
}
