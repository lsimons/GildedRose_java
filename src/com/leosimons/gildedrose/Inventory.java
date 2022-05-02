package com.leosimons.gildedrose;

@SuppressWarnings({"unused", "ClassCanBeRecord"})
public class Inventory {
    public static final int MINIMUM_QUALITY = 0;
    public static final int MAXIMUM_QUALITY = 50;
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

        if (item.getQuality() <= MINIMUM_QUALITY || item.getQuality() >= MAXIMUM_QUALITY) {
            // || name.equals("Sulfuras, Hand of Ragnaros")
            return;
        }

        decreaseSellIn(item);

        if (name.startsWith("Aged")) {
            updateQualityForAgedItem(item);
        } else if (name.startsWith("Backstage passes")) {
            updateQualityForBackstagePasses(item);
        } else {
            updateQualityForNormalItem(item);
        }
    }

    private void updateQualityForNormalItem(final Item item) {
        decreaseQuality(item);
        if (item.getSellIn() <= 0) {
            decreaseQuality(item);
        }
    }

    private void updateQualityForBackstagePasses(final Item item) {
        final int sellIn = item.getSellIn();
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
    }

    private void updateQualityForAgedItem(final Item item) {
        increaseQuality(item);
        // possible BUG: increases quality twice as fast after sellIn
        if (item.getSellIn() <= 0) {
            increaseQuality(item);
        }
    }

    private void decreaseSellIn(final Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void decreaseQuality(final Item item) {
        if (item.getQuality() > MINIMUM_QUALITY) {
            item.setQuality(item.getQuality() - 1);
        }
    }

    private void increaseQuality(final Item item) {
        if (item.getQuality() < MAXIMUM_QUALITY) {
            item.setQuality(item.getQuality() + 1);
        }
    }
}
