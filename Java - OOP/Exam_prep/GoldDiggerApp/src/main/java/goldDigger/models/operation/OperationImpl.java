package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;
import java.util.List;

public class OperationImpl implements Operation{

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> spotExhibits = spot.getExhibits();

        for (Discoverer currentDiscoverer : discoverers) {
            while (currentDiscoverer.canDig() && spotExhibits.iterator().hasNext()) {
                currentDiscoverer.dig();

                String currentExhibits = spotExhibits.iterator().next();
                currentDiscoverer.getMuseum().getExhibits().add(currentExhibits);
                spotExhibits.remove(currentExhibits);
            }
        }
    }
}