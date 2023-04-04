package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ShopTest {

    private Shop shop;

    @Before
    public void init() {
        shop = new Shop();
    }

    @Test
    public void getValueCells() {
        Map<String, Goods> shelves;
        shelves = new LinkedHashMap<>();
        shelves.put("Shelves1", null);
        shelves.put("Shelves2", null);
        shelves.put("Shelves3", null);
        shelves.put("Shelves4", null);
        shelves.put("Shelves5", null);
        shelves.put("Shelves6", null);
        shelves.put("Shelves7", null);
        shelves.put("Shelves8", null);
        shelves.put("Shelves9", null);
        shelves.put("Shelves10", null);
        shelves.put("Shelves11", null);
        shelves.put("Shelves12", null);
        Assert.assertEquals(shelves, shop.getShelves());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionShelvesNotExist() throws OperationNotSupportedException {
        Goods goods = new Goods("Ivo", "AB");
        shop.addGoods("AB", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionShelveIsTaken() throws OperationNotSupportedException {
        Goods goods = new Goods("Ivo", "AB");
        Goods goods1 = new Goods("Ivan", "AB");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExceptionGoodsOnShelve() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        Goods good2 = new Goods("Ivo", "AB");
        shop.addGoods("Shelves1", good);
        shop.addGoods("Shelves2", good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInMapException() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        Goods good2 = new Goods("Ivan", "AB");
        shop.addGoods("Shelves1", good);
        long exist = shop.getShelves().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(1, exist);
        shop.addGoods("Shelves1", good2);
    }

    @Test
    public void successfulAddGood() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        shop.addGoods("Shelves1", good);
        Assert.assertEquals(1, shop.getShelves().values().stream().filter(Objects::nonNull).count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionShelveNotExistRemove() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        shop.addGoods("Shelves1", good);
        shop.removeGoods("AB", good);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWrongShelve() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        Goods good2 = new Goods("Ivan", "AB");
        shop.addGoods("Shelves1", good);
        shop.addGoods("Shelves2", good2);
        shop.removeGoods("Shelves1", good2);
    }

    @Test
    public void removeToySuccessful() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        Goods good2 = new Goods("Ivan", "AB");
        shop.addGoods("Shelves1", good);
        shop.addGoods("Shelves2", good2);
        shop.removeGoods("Shelves1", good);
        Assert.assertEquals(1, shop.getShelves().values().stream().filter(Objects::nonNull).count());
    }

    @Test
    public void removeGood2() throws OperationNotSupportedException {
        Goods good = new Goods("Ivo", "AB");
        Goods good2 = new Goods("Ivan", "AB");
        shop.addGoods("Shelves1", good);
        shop.addGoods("Shelves2", good2);
        String result = shop.removeGoods("Shelves2", good2);
        long exist = shop.getShelves().values().stream().filter(Objects::nonNull).count();
        Assert.assertEquals(1, exist);
        Assert.assertEquals("Goods: AB is removed successfully!", result);
    }
}