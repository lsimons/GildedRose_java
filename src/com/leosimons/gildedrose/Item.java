/**
 *
 */
package com.leosimons.gildedrose;

public class Item {

    private final String name;
    private int sellIn;

    private int quality;

    public Item(final String name, final int sellIn, final int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(final int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(final int quality) {
        this.quality = quality;
    }
}
