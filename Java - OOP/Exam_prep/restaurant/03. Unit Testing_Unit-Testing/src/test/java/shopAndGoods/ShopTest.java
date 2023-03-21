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
    public void getValue() {
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
        Goods goods = new Goods("A", "ABC");
        shop.addGoods("100", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionShelvesExist() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        Goods goods2 = new Goods("B", "ABCd");
        shop.addGoods("A", goods);
        shop.addGoods("A", goods2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testExceptionGoodsIsIOnShelves() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods);
    }

    @Test
    public void successfulAddGood() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        shop.addGoods("Shelves1", goods);
        Assert.assertEquals(1, shop.getShelves().values().stream().filter(Objects::nonNull).count());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotExistShelves() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        Goods goods2 = new Goods("B", "CBA");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("A", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotExist() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        Goods goods2 = new Goods("B", "CBA");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods2);
        shop.removeGoods("Shelves1", goods2);
    }

    @Test
    public void removeSuccessful() throws OperationNotSupportedException {
        Goods goods = new Goods("A", "ABC");
        Goods goods2 = new Goods("B", "CBA");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves2", goods2);
        shop.removeGoods("Shelves1", goods);
        Assert.assertEquals(1, shop.getShelves().values().stream().filter(Objects::nonNull).count());
    }
}