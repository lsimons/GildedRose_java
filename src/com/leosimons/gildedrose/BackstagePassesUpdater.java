package com.leosimons.gildedrose;

class BackstagePassesUpdater extends DefaultItemUpdater {

    public static final int SELL_IN_DAYS_HYPE = 10;
    public static final int SELL_IN_DAYS_MEGA_HYPE = 5;

    @Override
    public boolean match(final Item item) {
        return item.getName().startsWith("Backstage passes");
    }

    @Override
    public void update(final Item item) {
        decreaseSellIn(item);
        final int sellIn = item.getSellIn();
        if (sellIn <= 0) {
            item.setQuality(0);
            return;
        }

        increaseQuality(item);
        if (sellIn < SELL_IN_DAYS_HYPE) {
            increaseQuality(item);
        }
        if (sellIn < SELL_IN_DAYS_MEGA_HYPE) {
            increaseQuality(item);
        }
    }
}
