package com.ashindigo.spinneryexample.handler;

import com.ashindigo.spinneryexample.SpinneryExample;
import com.ashindigo.spinneryexample.entity.SampleChestEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import spinnery.common.handler.BaseScreenHandler;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;

public class SampleChestHandler extends BaseScreenHandler {

    public static final int INVENTORY = 1;

    public SampleChestHandler(int synchronizationID, PlayerInventory playerInventory, BlockPos pos) {
        super(synchronizationID, playerInventory);
        WInterface mainInterface = getInterface();
        addInventory(INVENTORY, (SampleChestEntity) world.getBlockEntity(pos));
        WSlot.addHeadlessArray(mainInterface, 0, INVENTORY, 9, 3);
        WSlot.addHeadlessPlayerInventory(mainInterface);
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return SpinneryExample.sampleChestHandler;
    }
}
