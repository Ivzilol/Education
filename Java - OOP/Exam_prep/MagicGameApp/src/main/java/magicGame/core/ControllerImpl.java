package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.MagicianImpl;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {


    private MagicRepositoryImpl magics;

    private MagicianRepositoryImpl magicians;

    private RegionImpl region;

    public ControllerImpl() {
        magics = new MagicRepositoryImpl();
        magicians = new MagicianRepositoryImpl();
        region = new RegionImpl();
    }


    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        String message = "";
        switch (type) {
            case "RedMagic":
                RedMagic redMagic = new RedMagic(name, bulletsCount);
                magics.addMagic(redMagic);
                message = String.format("Successfully added magic %s.", name);
                break;
            case "BlackMagic":
                BlackMagic blackMagic = new BlackMagic(name, bulletsCount);
                magics.addMagic(blackMagic);
                message = String.format("Successfully added magic %s.", name);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }
        return message;
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        MagicianImpl magician;
        Magic magic = (Magic) magics.findByName(magicName);
        String message = "";
        if (magic == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }
        switch (type) {
            case "Wizard":
                magician = new Wizard(username, health, protection, magic);
                magicians.addMagician(magician);
                message = String.format("Successfully added magician %s.", username);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magic);
                magicians.addMagician(magician);
                message = String.format("Successfully added magician %s.", username);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }
        return message;
    }

    @Override
    public String startGame() {
        return region.start(magicians.getData());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        List<Magician> magicianList = magicians.getData().stream().sorted(Comparator.comparing(Magician::getHealth).thenComparing(Magician::getUsername)).collect(Collectors.toList());
        for (Magician magician : magicianList) {
            int health = magician.getHealth();
            if (magician.getHealth() < 0){
                health = 0;
            }
            int protection = magician.getProtection();
            if (magician.getProtection() < 0){
                protection = 0;
            }
            sb.append(String.format("%s: %s", magician.getClass().getSimpleName(), magician.getUsername())).append(System.lineSeparator())
                    .append(String.format("Health: %d", health)).append(System.lineSeparator())
                    .append(String.format("Protection: %d", protection)).append(System.lineSeparator())
                    .append(String.format("Magic: %s", magician.getMagic().getName())).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
