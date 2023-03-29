package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {

    private GunRepository<Gun> gunGunRepository = new GunRepository<>();

    private Player mainPlayer = new MainPlayer("Tommy Vercetti");

    private Collection<Player> civilPlayers = new ArrayList<>();



    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        civilPlayers.add(player);
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Pistol":
                gun = new Pistol(name);
                break;
            case "Rifle":
                gun = new Rifle(name);
                break;
            default:
                return String.format(GUN_TYPE_INVALID);
        }
        this.gunGunRepository.add(gun);
        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = this.gunGunRepository.getModels().stream().findFirst().orElse(null);
        if (gun == null) {
            return String.format(GUN_QUEUE_IS_EMPTY);
        } else {
            if (name.equals("Vercetti")) {
                mainPlayer.getGunRepository().add(gun);
                this.gunGunRepository.remove(gun);
                return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
            }
            Player player = this.civilPlayers.stream()
                    .filter(p -> p.getName().equals(name)).findFirst().orElse(null);
            if (player == null) {
                return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
            } else {
                player.getGunRepository().add(gun);
                this.gunGunRepository.remove(gun);
                return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
            }
        }
    }

    @Override
    public String fight() {
        int initialLifePointsMainPlayer = mainPlayer.getLifePoints();
        int lifePointsOnCivilianPlayers = 0;
        for (Player player : this.civilPlayers) {
            lifePointsOnCivilianPlayers += player.getLifePoints();
        }
        int initialNumberCivilPlayers = this.civilPlayers.size();
        Neighbourhood neighbourhood = new GangNeighbourhood();
        neighbourhood.action(mainPlayer, civilPlayers);
        int civilHealthPointsAfetAction = (int) this.civilPlayers.stream().mapToInt(Player::getLifePoints).sum();
        if (mainPlayer.getLifePoints() == initialLifePointsMainPlayer && lifePointsOnCivilianPlayers == civilHealthPointsAfetAction) {
            return String.format(FIGHT_HOT_HAPPENED);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(FIGHT_HAPPENED));
            sb.append(System.lineSeparator());
            sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()));
            sb.append(System.lineSeparator());
            sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,
                    initialNumberCivilPlayers - this.civilPlayers.size()));
            sb.append(System.lineSeparator());
            sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size()));
            return sb.toString().trim();
        }

    }
}
