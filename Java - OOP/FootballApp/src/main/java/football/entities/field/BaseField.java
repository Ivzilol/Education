package football.entities.field;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseField implements Field {

    private String name;
    private int capacity;

    private Collection<Supplement> supplements;

    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            String exceptionMessage = ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY;
            throw new NullPointerException(exceptionMessage);
        } else {
            this.name = name;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(Collection<Supplement> supplements) {
        this.supplements = supplements;
    }

    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<Player> players) {
        this.players = players;
    }

    @Override
    public int sumEnergy() {
        int allEnergy = 0;
        for (Supplement supplement : supplements) {
            allEnergy += supplement.getEnergy();
        }
        return allEnergy;
    }

    @Override
    public void addPlayer(Player player) {
        if (player.getKg() < capacity) {
            players.add(player);
        } else {
            String exceptionMessage = ConstantMessages.NOT_ENOUGH_CAPACITY;
            throw new IllegalStateException(exceptionMessage);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
        for (Player player : this.players) {
            player.stimulation();
        }
    }

    @Override
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s (%s):", this.name, this.getClass().getSimpleName()));
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Player: ");
        if (players.isEmpty()) {
            stringBuilder.append("none");
        } else {
            for (Player player : players) {
                stringBuilder.append(String.format("%s ", player.getName()));
            }
        }
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Supplement: ");
        stringBuilder.append(this.supplements.size());
        stringBuilder.append(System.lineSeparator());
        stringBuilder.append("Energy: ");
        stringBuilder.append(this.sumEnergy());
        return stringBuilder.toString().trim();
    }
}
