package com.ashindigo.spinneryexample.block;

import com.ashindigo.spinneryexample.entity.SampleChestEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SampleChestBlock extends BlockWithEntity {

    public SampleChestBlock() {
        super(FabricBlockSettings.of(Material.WOOD));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            player.openHandledScreen((ExtendedScreenHandlerFactory) world.getBlockEntity(pos));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new SampleChestEntity();
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getWorld().getBlockEntity(pos);
        if (blockEntity instanceof SampleChestEntity) {
            ItemScatterer.spawn(world.getWorld(), pos, (SampleChestEntity) blockEntity);
            world.getWorld().updateComparators(pos, this);
        }
        super.onBreak(world, pos, state, player);
    }
}
