package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {

    private final Map<String, Deliverer> deliverers = new LinkedHashMap<>();

    private final Map<String, Package> packages = new LinkedHashMap<>();

    private final Map<String, Integer> packagesByDeliverer = new LinkedHashMap<>();

    private final Map<String, Package> unassignedPackages = new LinkedHashMap<>();

    @Override
    public void addDeliverer(Deliverer deliverer) {
        deliverers.put(deliverer.getId(), deliverer);
        packagesByDeliverer.put(deliverer.getId(), 0);

    }

    @Override
    public void addPackage(Package _package) {
        packages.put(_package.getId(), _package);
        unassignedPackages.put(_package.getId(), _package);
    }

    @Override
    public boolean contains(Deliverer deliverer) {
        return deliverers.get(deliverer.getId()) != null;
    }

    @Override
    public boolean contains(Package _package) {
        return packages.get(_package.getId()) != null;
    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
        return deliverers.values();
    }

    @Override
    public Iterable<Package> getPackages() {
        return packages.values();
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {
        if (!contains(deliverer) || !contains(_package)) {
            throw new IllegalArgumentException();
        }
        int currentCount = packagesByDeliverer.get(deliverer.getId());
        packagesByDeliverer.put(deliverer.getId(), currentCount + 1);
        unassignedPackages.remove(_package.getId());
    }

    @Override
    public Iterable<Package> getUnassignedPackages() {
        return unassignedPackages.values();
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
        return packages.values()
                .stream()
                .sorted(Comparator.comparing(Package::getWeight).reversed()
                        .thenComparing(Package::getReceiver))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {
        return deliverers.values()
                .stream()
                .sorted(Comparator.comparing((Deliverer d) -> packagesByDeliverer.get(d.getId())).reversed()
                        .thenComparing(Deliverer::getName))
                .collect(Collectors.toList());
    }
}
