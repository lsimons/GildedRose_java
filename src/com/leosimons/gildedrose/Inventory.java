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
        final String name = item.getName();

        if (item.getQuality() <= 0 || item.getQuality() >= 50) {
            // || name.equals("Sulfuras, Hand of Ragnaros")
            return;
        }

        decreaseSellIn(item);

        final int sellIn = item.getSellIn();
        if (name.startsWith("Aged")) {
            increaseQuality(item);
            // possible BUG: increases quality twice as fast after sellIn
            if (sellIn <= 0) {
                increaseQuality(item);
            }
        } else if (name.startsWith("Backstage passes")) {
            if (sellIn <= 0) {
                item.setQuality(0);
            } else {
                increaseQuality(item);
                if (sellIn < 10) {
                    increaseQuality(item);
                }
                if (sellIn < 5) {
                    increaseQuality(item);
                }
            }
        } else {
            decreaseQuality(item);
            if (sellIn <= 0) {
                decreaseQuality(item);
            }
        }
    }

    private void decreaseSellIn(final Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void decreaseQuality(final Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void increaseQuality(final Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}
