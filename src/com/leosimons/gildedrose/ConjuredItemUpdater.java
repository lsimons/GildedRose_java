package com.leosimons.gildedrose;

class ConjuredItemUpdater extends DefaultItemUpdater {
    @Override
    public boolean match(final Item item) {
        return item.getName().startsWith("Conjured");
    }

    @Override
    public void update(final Item item) {
        decreaseSellIn(item);
        decreaseQuality(item);
        decreaseQuality(item);
        if (item.getSellIn() <= 0) {
            decreaseQuality(item);
            decreaseQuality(item);
        }
    }
}
