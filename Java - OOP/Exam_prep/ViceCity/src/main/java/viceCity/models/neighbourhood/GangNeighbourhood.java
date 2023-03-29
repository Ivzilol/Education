package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.BasePlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        List<Gun> mainPlayerGuns = new ArrayList<>(mainPlayer.getGunRepository().getModels());
        boolean ifEmpty = mainPlayerGuns.isEmpty();
        while (!civilPlayers.isEmpty() && mainPlayer.isAlive()) {
            Player player = civilPlayers.stream().filter(Player::isAlive).findFirst().orElse(null);
            List<Gun> currentPlayerGun = new ArrayList<>(player.getGunRepository().getModels());
            if (mainPlayerGuns.isEmpty() && !currentPlayerGun.isEmpty()) {
                Gun currentGunCivilPlayer = currentPlayerGun.get(0);
                if (currentGunCivilPlayer.canFire()) {
                    mainPlayer.takeLifePoints(currentGunCivilPlayer.fire());
                } else {
                    currentPlayerGun.remove(currentGunCivilPlayer);
                }
                continue;
            }
            if (ifEmpty) {
                break;
            }
            Gun currentGun = mainPlayerGuns.get(0);
            if (currentGun.canFire()) {
                player.takeLifePoints(currentGun.fire());
                if (!player.isAlive()) {
                    civilPlayers.remove(player);
                }
            } else {
                mainPlayerGuns.remove(currentGun);
            }
        }
    }
}
