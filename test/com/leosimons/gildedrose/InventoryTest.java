package com.leosimons.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {
//	new Item("+5 Dexterity Vest", 10, 20), 
//	new Item("Aged Brie", 2, 0),
//	new Item("Elixir of the Mongoose", 5, 7),
//	new Item("Sulfuras, Hand of Ragnaros", 0, 80),
//	new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
//	new Item("Conjured Mana Cake", 3, 6)

    @Test
    public void shouldNeverChangesQualityOfSulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        Inventory sut = new Inventory(sulfuras);

        sut.updateQuality();

        assertEquals(80, sulfuras.getQuality());
    }

    @Test
    public void shouldNeverChangesSellInOfSulfuras() {
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        Inventory sut = new Inventory(sulfuras);

        sut.updateQuality();

        assertEquals(0, sulfuras.getSellIn());

    }

    @Test
    public void shouldLowerTheSellInByOneForNormalItems() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);

        Inventory sut = new Inventory(normalItem);

        sut.updateQuality();

        assertEquals(9, normalItem.getSellIn());
    }

    @Test
    public void shouldLowerTheQualityByOneForNormalItems() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 20);

        Inventory sut = new Inventory(normalItem);

        sut.updateQuality();

        assertEquals(19, normalItem.getQuality());
    }

    @Test
    public void shouldNotLowerTheQualityBelowZero() {
        Item normalItem = new Item("+5 Dexterity Vest", 10, 0);

        Inventory sut = new Inventory(normalItem);

        sut.updateQuality();

        assertEquals(0, normalItem.getQuality());
    }

    @Test
    public void shouldLowerTheQualityTwiceAsFastOnceTheSellInDateHasPassed() {
        Item normalItem = new Item("+5 Dexterity Vest", -1, 25);

        Inventory sut = new Inventory(normalItem);

        sut.updateQuality();

        assertEquals(23, normalItem.getQuality());
    }

    @Test
    public void shouldIncreaseTheQualityOfAgedBrieAsItGetsOlder() {
        Item agedBrie = new Item("Aged Brie", 10, 25);

        Inventory sut = new Inventory(agedBrie);

        sut.updateQuality();

        assertEquals(26, agedBrie.getQuality());
    }

    @Test
    public void shouldNotIncreaseTheQualityOfAgedBrieOver50() {
        Item agedBrie = new Item("Aged Brie", 10, 50);

        Inventory sut = new Inventory(agedBrie);

        sut.updateQuality();

        assertEquals(50, agedBrie.getQuality());
    }

    @Test
    public void shouldNeverChangesSellInOfAgedBrie() {
        Item agedBrie = new Item("Aged Brie", 0, 25);

        Inventory sut = new Inventory(agedBrie);

        sut.updateQuality();

        assertEquals(0, agedBrie.getSellIn());
    }

    @Test
    public void shouldLowerBackstagePassesToZeroQualityOnceConcertHasHappened() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20);

        Inventory sut = new Inventory(backStagePass);

        sut.updateQuality();

        assertEquals(0, backStagePass.getQuality());
    }

    @Test
    public void shouldIncreaseBackstagePassesQualityBy1WhenTheConcertIsMoreThan10DaysAway() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20);

        Inventory sut = new Inventory(backStagePass);

        sut.updateQuality();

        assertEquals(21, backStagePass.getQuality());
    }

    @Test
    public void shouldIncreaseBackstagePassesQualityBy2WhenTheConcertIs10DaysOrLessAway() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 27);

        Inventory sut = new Inventory(backStagePass);

        sut.updateQuality();

        assertEquals(29, backStagePass.getQuality());
    }

    @Test
    public void shouldIncreaseBackstagePassesQualityBy3WhenTheConcertIs5DaysOrLessAway() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 44);

        Inventory sut = new Inventory(backStagePass);

        sut.updateQuality();

        assertEquals(47, backStagePass.getQuality());
    }

    @Test
    public void shouldNotIncreaseBackstagePassesAboveAQualityOf50() {
        Item backStagePassMoreThan10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50);

        Item backStagePass10DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Item backStagePass5DaysAway = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);

        Inventory sut = new Inventory(backStagePassMoreThan10DaysAway, backStagePass10DaysAway, backStagePass5DaysAway);

        sut.updateQuality();

        assertEquals(50, backStagePassMoreThan10DaysAway.getQuality());
        assertEquals(50, backStagePass10DaysAway.getQuality());
        assertEquals(50, backStagePass5DaysAway.getQuality());
    }

    @Test
    public void shouldLowerTheSellInByOneForBackstagePasses() {
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 44);

        Inventory sut = new Inventory(backStagePass);

        sut.updateQuality();

        assertEquals(9, backStagePass.getSellIn());
    }

    @Test
    public void shouldLowerTheSellInByOneForConjuredItems() {
        Item conjuredItem = new Item("Conjured Mana Cake", 3, 6);

        Inventory sut = new Inventory(conjuredItem);

        sut.updateQuality();

        assertEquals(2, conjuredItem.getSellIn());
    }

    @Test
    public void shouldLowerTheQualityByTwoForConjuredItems() {
        Item conjuredItem = new Item("Conjured Mana Cake", 3, 6);

        Inventory sut = new Inventory(conjuredItem);

        sut.updateQuality();

        assertEquals(4, conjuredItem.getQuality());
    }

    @Test
    public void shouldNotLowerTheQualityBelowZeroForConjuredItems() {
        Item conjuredItem = new Item("Conjured Mana Cake", 1, 1);

        Inventory sut = new Inventory(conjuredItem);

        sut.updateQuality();

        assertEquals(0, conjuredItem.getQuality());
    }
}
