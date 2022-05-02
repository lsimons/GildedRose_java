package com.leosimons.gildedrose;

interface ItemQualityUpdater {
    boolean match(Item item);

    void update(Item item);

}
