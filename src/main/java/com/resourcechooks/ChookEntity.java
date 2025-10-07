package com.resourcechooks;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public interface ChookEntity {
    int getRadioactivity();

    Item getLayItem();
    void setLayItem(Item item);

    void layItem(World world);
}
