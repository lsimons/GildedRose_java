package com.leosimons.gildedrose;

interface ItemUpdater {
    boolean match(Item item);

    void update(Item item);

}
