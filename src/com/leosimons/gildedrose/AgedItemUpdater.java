package com.leosimons.gildedrose;

class AgedItemUpdater extends DefaultItemUpdater {
    @Override
    public boolean match(final Item item) {
        return item.getName().startsWith("Aged");
    }

    @Override
    public void update(final Item item) {
        increaseQuality(item);
    }
}
