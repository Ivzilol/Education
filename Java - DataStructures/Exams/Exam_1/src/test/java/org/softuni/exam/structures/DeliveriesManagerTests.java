package org.softuni.exam.structures;

import org.junit.Before;
import org.junit.Test;
import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class DeliveriesManagerTests {
    private interface InternalTest {
        void execute();
    }

    private DeliveriesManager deliveriesManager;

    private Deliverer getRandomDeliverer() {
        return new Deliverer(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString());
    }

    private Package getRandomPackage() {
        return new Package(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Math.min(1, Math.random() * 1_000_000_000));
    }

    @Before
    public void setup() {
        this.deliveriesManager = new DeliveriesManagerImpl();
    }

    public void performCorrectnessTesting(InternalTest[] methods) {
        Arrays.stream(methods)
                .forEach(method -> {
                    this.deliveriesManager = new DeliveriesManagerImpl();

                    try {
                        method.execute();
                    } catch (IllegalArgumentException ignored) { }
                });

        this.deliveriesManager = new DeliveriesManagerImpl();
    }

    // Correctness Tests

    @Test
    public void testContains_WithExistentDeliverer_ShouldReturnTrue() {
        Deliverer deliverer = getRandomDeliverer();
        this.deliveriesManager.addDeliverer(deliverer);

        assertTrue(this.deliveriesManager.contains(deliverer));
    }

    @Test
    public void testContains_WithNonExistentDeliverer_ShouldReturnFalse() {
        this.deliveriesManager.addDeliverer(getRandomDeliverer());

        assertFalse(this.deliveriesManager.contains(getRandomDeliverer()));
    }

    @Test
    public void testContains_WithExistentPackage_ShouldReturnTrue() {
        Package _package = getRandomPackage();
        this.deliveriesManager.addPackage(_package);

        assertTrue(this.deliveriesManager.contains(_package));
    }

    @Test
    public void testContains_WithNonExistentPackage_ShouldReturnFalse() {
        this.deliveriesManager.addPackage(getRandomPackage());

        assertFalse(this.deliveriesManager.contains(getRandomPackage()));
    }

    @Test
    public void testGetDeliverers_WithSeveralDeliverers_ShouldReturnCorrectResults() {
        Deliverer deliverer1 = getRandomDeliverer();
        Deliverer deliverer2 = getRandomDeliverer();
        Deliverer deliverer3 = getRandomDeliverer();

        this.deliveriesManager.addDeliverer(deliverer1);
        this.deliveriesManager.addDeliverer(deliverer2);
        this.deliveriesManager.addDeliverer(deliverer3);

        Set<Deliverer> set =
                StreamSupport.stream(this.deliveriesManager.getDeliverers().spliterator(), false)
                        .collect(Collectors.toSet());

        assertEquals(set.size(), 3);
        assertTrue(set.contains(deliverer1));
        assertTrue(set.contains(deliverer2));
        assertTrue(set.contains(deliverer3));
    }

    @Test
    public void testGetDeliverers_WithEmptyCollection_ShouldReturnCorrectResults() {
        assertEquals(StreamSupport.stream(this.deliveriesManager.getDeliverers().spliterator(), false)
                .collect(Collectors.toSet()).size(), 0);
    }

    @Test
    public void testUnassignedPackages_WithSeveralPackages_ShouldReturnCorrectResults() {
        Deliverer deliverer = getRandomDeliverer();
        Package _package1 = getRandomPackage();
        Package _package2 = getRandomPackage();
        Package _package3 = getRandomPackage();
        Package _package4 = getRandomPackage();

        this.deliveriesManager.addDeliverer(deliverer);
        this.deliveriesManager.addPackage(_package1);
        this.deliveriesManager.addPackage(_package2);
        this.deliveriesManager.addPackage(_package3);
        this.deliveriesManager.addPackage(_package4);

        this.deliveriesManager.assignPackage(deliverer, _package1);
        this.deliveriesManager.assignPackage(deliverer, _package2);

        Set<Package> set =
                StreamSupport.stream(this.deliveriesManager.getUnassignedPackages().spliterator(), false)
                        .collect(Collectors.toSet());

        assertEquals(set.size(), 2);
        assertTrue(set.contains(_package3));
        assertTrue(set.contains(_package4));
    }

    // Performance Tests

    @Test
    public void testContainsDeliverer_With100000Results_ShouldPassQuickly() {
        this.performCorrectnessTesting(new InternalTest[] {
                this::testContains_WithExistentDeliverer_ShouldReturnTrue,
                this::testContains_WithNonExistentDeliverer_ShouldReturnFalse
        });

        int count = 100000;

        Deliverer deliverer = null;

        for (int i = 0; i < count; i++)
        {
            if(i == count / 2) {
                deliverer = getRandomDeliverer();
                this.deliveriesManager.addDeliverer(deliverer);
            } else {
                this.deliveriesManager.addDeliverer(getRandomDeliverer());
            }

        }

        long start = System.currentTimeMillis();

        boolean _contains = this.deliveriesManager.contains(deliverer);

        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

        assertTrue(_contains);
        assertTrue(elapsedTime <= 5);
    }

    @Test
    public void testContainsPackage_With100000Results_ShouldPassQuickly() {
        this.performCorrectnessTesting(new InternalTest[] {
                this::testContains_WithExistentPackage_ShouldReturnTrue,
                this::testContains_WithNonExistentPackage_ShouldReturnFalse
        });

        int count = 100000;

        Package _package = null;

        for (int i = 0; i < count; i++)
        {
            if(i == count / 2) {
                _package = getRandomPackage();
                this.deliveriesManager.addPackage(_package);
            } else {
                this.deliveriesManager.addPackage(getRandomPackage());
            }

        }

        long start = System.currentTimeMillis();

        boolean _contains = this.deliveriesManager.contains(_package);

        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

        assertTrue(_contains);
        assertTrue(elapsedTime <= 5);
    }

    @Test
    public void testGetDeliverers_With100000Results_ShouldPassQuickly() {
        this.performCorrectnessTesting(new InternalTest[] {
            this::testGetDeliverers_WithSeveralDeliverers_ShouldReturnCorrectResults,
            this::testGetDeliverers_WithEmptyCollection_ShouldReturnCorrectResults
        });

        int count = 100000;

        for (int i = 0; i < count; i++)
        {
            this.deliveriesManager.addDeliverer(getRandomDeliverer());
        }

        long start = System.currentTimeMillis();

        this.deliveriesManager.getDeliverers();

        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

        assertTrue(elapsedTime <= 5);
    }
}
