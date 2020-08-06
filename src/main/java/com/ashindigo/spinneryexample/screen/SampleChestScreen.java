package com.ashindigo.spinneryexample.screen;

import com.ashindigo.spinneryexample.handler.SampleChestHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import spinnery.client.screen.BaseHandledScreen;
import spinnery.widget.WInterface;
import spinnery.widget.WPanel;
import spinnery.widget.WSlot;
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;

public class SampleChestScreen extends BaseHandledScreen<SampleChestHandler> {

    public SampleChestScreen(SampleChestHandler handler, PlayerInventory playerInv, Text name) {
        super(name, handler, playerInv.player);
        WInterface mainInterface = getInterface();
        WPanel panel = mainInterface.createChild(WPanel::new, Position.of(mainInterface), Size.of(176, 168));
        panel.center();
        panel.setLabel(name);
        WSlot.addArray(Position.of(panel).add(6, 18, 1), Size.of(18, 18), panel, 0, SampleChestHandler.INVENTORY, 9, 3);
        WSlot.addPlayerInventory(Position.of(panel).add(6, 85, 1), Size.of(18, 18), panel);
    }
}
