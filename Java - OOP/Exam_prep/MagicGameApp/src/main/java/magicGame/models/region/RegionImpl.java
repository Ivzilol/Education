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
        List<Magician> wizardList = new ArrayList<>();
        List<Magician> blackWidowList = new ArrayList<>();

        for (Magician magician : magicians) {
            if (magician.getClass().getSimpleName().equals("Wizard")){
                wizardList.add(magician);
            } else if (magician.getClass().getSimpleName().equals("BlackWidow")){
                blackWidowList.add(magician);
            }
        }
        while(!wizardList.isEmpty() && !blackWidowList.isEmpty()){
            Wizard wizard = (Wizard) wizardList.get(0);
            BlackWidow blackWidow = (BlackWidow) blackWidowList.get(0);

            blackWidow.takeDamage(wizard.getMagic().fire());
            if (blackWidow.isAlive()){
                wizard.takeDamage(blackWidow.getMagic().fire());
                if (!wizard.isAlive()){
                    wizardList.remove(wizard);
                }
            } else {
                blackWidowList.remove(blackWidow);
            }
        }
        if (wizardList.size() > blackWidowList.size()){
            return "Wizards win!";
        } else {
            return "Black widows win!";
        }
    }
}
