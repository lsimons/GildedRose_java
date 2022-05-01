package com.leosimons.gildedrose;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Inventory {
    private final Item[] items;

    public Inventory(final Item... items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            updateQuality(item);
        }
    }

    private void updateQuality(final Item item) {
        final int quality = item.getQuality();
        if (quality <= 0 || quality >= 50) {
            // includes legendary items, like "Sulfuras, Hand of Ragnaros"
            return;
        }

        final String name = item.getName();
        if (name.startsWith("Aged")) {
            updateQualityOfAgedItems(item);
        } else if (name.startsWith("Backstage passes")) {
            updateQualityOfBackstagePasses(item);
        } else if (name.startsWith("Conjured")) {
            updateQualityOfConjuredItems(item);
        } else {
            updateQualityOfNormalItems(item);
        }
    }

    private void updateQualityOfNormalItems(final Item item) {
        reduceQuality(item);

        final int sellIn = item.getSellIn();
        if (sellIn < 0) {
            reduceQuality(item);
        }

        reduceSellIn(item);
    }

    private void updateQualityOfAgedItems(final Item item) {
        increaseQuality(item);
    }

    private void updateQualityOfBackstagePasses(final Item item) {
        final int sellIn = item.getSellIn();
        if (sellIn < 0) {
            item.setQuality(0);
        } else if (sellIn <= 5) {
            increaseQuality(item);
            increaseQuality(item);
            increaseQuality(item);
        } else if (sellIn <= 10) {
            increaseQuality(item);
            increaseQuality(item);
        } else {
            increaseQuality(item);
        }

        reduceSellIn(item);
    }

    private void updateQualityOfConjuredItems(final Item item) {
        reduceQuality(item);
        reduceQuality(item);

        final int sellIn = item.getSellIn();
        if (sellIn < 0) {
            reduceQuality(item);
            reduceQuality(item);
        }

        reduceSellIn(item);
    }

    private void reduceSellIn(final Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void reduceQuality(final Item item) {
        final int quality = item.getQuality();
        if (quality > 0) {
            item.setQuality(quality - 1);
        }
    }

    private void increaseQuality(final Item item) {
        final int quality = item.getQuality();
        if (quality < 50) {
            item.setQuality(quality + 1);
        }
    }
}
