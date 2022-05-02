package com.leosimons.gildedrose;

class AgedItemQualityUpdater extends DefaultItemQualityUpdater {
    @Override
    public boolean match(final Item item) {
        return item.getName().startsWith("Aged");
    }

    @Override
    public void update(final Item item) {
        decreaseSellIn(item);
        increaseQuality(item);
        // possible BUG: increases quality twice as fast after sellIn
        if (item.getSellIn() <= 0) {
            increaseQuality(item);
        }
    }
}
