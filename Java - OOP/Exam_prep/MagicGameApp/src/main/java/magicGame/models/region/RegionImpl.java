package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegionImpl implements Region{


    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizards = new ArrayList<>();
        List<Magician> blackWidow = new ArrayList<>();
        for (Magician magician : magicians) {
            if (magician.getClass().getSimpleName().equals("Wizard")) {
                wizards.add(magician);
            } else if (magician.getClass().getSimpleName().equals("BlackWidow")) {
                blackWidow.add(magician);
            }
        }
        while (!wizards.isEmpty() && !blackWidow.isEmpty()) {
            Wizard currentWizard = (Wizard) wizards.get(0);
            BlackWidow currentBlackWidow = (BlackWidow) blackWidow.get(0);
            currentBlackWidow.takeDamage(currentWizard.getMagic().fire());
            if (currentBlackWidow.isAlive()) {
                currentWizard.takeDamage(currentBlackWidow.getMagic().fire());
                if (!currentWizard.isAlive()) {
                    wizards.remove(currentBlackWidow);
                }
            } else {
                blackWidow.remove(currentBlackWidow);
            }
        }
        if (wizards.size() > blackWidow.size()) {
            return "Wizards win!";
        } else {
            return "Black widows win!";
        }
    }
}
