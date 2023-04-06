package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class BankVaultTest {

    private BankVault bankVault;

    @Before
    public void init() {
        bankVault = new BankVault();
    }

    @Test
    public void getValueCells() {
        Map<String, Item> vaultCells;
        vaultCells = new LinkedHashMap<>();
        vaultCells.put("A1", null);
        vaultCells.put("A2", null);
        vaultCells.put("A3", null);
        vaultCells.put("A4", null);
        vaultCells.put("B1", null);
        vaultCells.put("B2", null);
        vaultCells.put("B3", null);
        vaultCells.put("B4", null);
        vaultCells.put("C1", null);
        vaultCells.put("C2", null);
        vaultCells.put("C3", null);
        vaultCells.put("C4", null);
        Assert.assertEquals(vaultCells, bankVault.getVaultCells());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoShell() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        this.bankVault.addItem("BB", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShellBusy() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A1", item2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testItemIsAdd() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A2", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInMapException() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        long exist = this.bankVault.getVaultCells().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(1, exist);
        bankVault.addItem("A1", item);
    }

    @Test
    public void successfulAddItem() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        this.bankVault.addItem("A1", item);
        Assert.assertEquals(1, bankVault.getVaultCells().values().stream().filter(Objects::nonNull).count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShellNotExistRemove() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        bankVault.removeItem("AB", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testItemForRemoveNotExist() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        bankVault.removeItem("A1", item2);
    }

    @Test
    public void successfulRemoveItem() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A2", item2);
        bankVault.removeItem("A1", item);
        Assert.assertEquals(1, bankVault.getVaultCells().values().stream().filter(Objects::nonNull).count());

    }

    @Test
    public void remove2() throws OperationNotSupportedException {
        Item item = new Item("Ivo", "ID");
        Item item2 = new Item("Ivan", "ID");
        this.bankVault.addItem("A1", item);
        this.bankVault.addItem("A2", item2);
        String result = bankVault.removeItem("A2", item2);
        long exist = bankVault.getVaultCells().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(1, exist);
        Assert.assertEquals("Remove item:ID successfully!", result);
    }
}