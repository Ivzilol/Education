package gifts;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GiftFactoryTests {

    @Test
    public void getCountTest() {
        Gift gift = new Gift("Magic", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        Assert.assertEquals(1, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGift() {
        Gift gift = new Gift("Magic", 100);
        Gift gift2 = new Gift("Magic", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        giftFactory.createGift(gift2);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftExceptionNull() {
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftExceptionWhitespace() {
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.removeGift("     ");
    }

    @Test
    public void successRemove() {
        Gift gift = new Gift("Magic", 100);
        Gift gift2 = new Gift("Magic2", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        giftFactory.createGift(gift2);
        giftFactory.removeGift("Magic");
        Assert.assertEquals(1, giftFactory.getCount());
    }

    @Test
    public void getLeastMagicTest() {
        Gift gift = new Gift("Magic", 120);
        Gift gift2 = new Gift("Magic2", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        giftFactory.createGift(gift2);
        Assert.assertEquals(gift2, giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getLeastMagicNullTest() {
        GiftFactory giftFactory = new GiftFactory();
        Assert.assertNull(giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getGiftTest() {
        Gift gift = new Gift("Magic", 120);
        Gift gift2 = new Gift("Magic2", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        giftFactory.createGift(gift2);
        Assert.assertEquals(gift2, giftFactory.getPresent("Magic2"));
    }

    @Test
    public void getGiftByNameNullTest() {
        GiftFactory giftFactory = new GiftFactory();
        Assert.assertNull(giftFactory.getPresent("Magic"));
    }

    @Test
    public void getPresentsTest() {
        Gift gift = new Gift("Magic", 120);
        Gift gift2 = new Gift("Magic2", 100);
        GiftFactory giftFactory = new GiftFactory();
        giftFactory.createGift(gift);
        giftFactory.createGift(gift2);


        Assert.assertEquals(Collections.unmodifiableCollection(giftFactory.getPresents()), giftFactory.getPresents());
    }

}
