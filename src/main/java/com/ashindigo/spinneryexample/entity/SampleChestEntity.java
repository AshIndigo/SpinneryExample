package com.ashindigo.spinneryexample.entity;

import com.ashindigo.spinneryexample.SpinneryExample;
import com.ashindigo.spinneryexample.handler.SampleChestHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import spinnery.common.inventory.BaseInventory;
import spinnery.common.utility.InventoryUtilities;

public class SampleChestEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory {

    final DefaultedList<ItemStack> stacks;

    public SampleChestEntity() {
        super(SpinneryExample.sampleChestEntity);
        this.stacks = DefaultedList.ofSize(size(), ItemStack.EMPTY);
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        return stacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = stacks.get(slot).split(amount);
        markDirty();
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack stack = stacks.remove(slot);
        markDirty();
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        stacks.set(slot, stack);
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        stacks.clear();
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return true; // If you need to control what is valid for each slot, otherwise just don't even override
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(getPos());
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText("block.spinneryexample.samplechest");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SampleChestHandler(syncId, inv, getPos()); // pos would also work instead of getPos()
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        if (tag.contains("inventory")) {
            BaseInventory inv = InventoryUtilities.read(tag); // Yep, you can cheap out here
            for (int i = 0; i < inv.size(); i++) {
                setStack(i, inv.getStack(i));
            }
        }
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        InventoryUtilities.write(this, tag);
        super.toTag(tag);
        return tag;
    }
}
