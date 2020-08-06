package com.ashindigo.spinneryexample;

import com.ashindigo.spinneryexample.block.SampleChestBlock;
import com.ashindigo.spinneryexample.entity.SampleChestEntity;
import com.ashindigo.spinneryexample.handler.SampleChestHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SpinneryExample implements ModInitializer {

    public static final String MODID = "spinneryexample";
    public static BlockEntityType<SampleChestEntity> sampleChestEntity;
    public static SampleChestBlock sampleChest;
    public static ExtendedScreenHandlerType<SampleChestHandler> sampleChestHandler;

    @Override
    public void onInitialize() {
        sampleChest = Registry.register(Registry.BLOCK, new Identifier(MODID, "samplechest"), new SampleChestBlock());
        Registry.register(Registry.ITEM, new Identifier(MODID, "samplechest"), new BlockItem(sampleChest, new Item.Settings().group(ItemGroup.MISC)));
        sampleChestEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(MODID, "samplechest"), BlockEntityType.Builder.create(SampleChestEntity::new, sampleChest).build(null));
        sampleChestHandler = (ExtendedScreenHandlerType<SampleChestHandler>) ScreenHandlerRegistry.registerExtended(new Identifier(MODID, "samplechest"), (syncId, inventory, buf) -> new SampleChestHandler(syncId, inventory, buf.readBlockPos()));

    }
}
