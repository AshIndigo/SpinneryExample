package com.ashindigo.spinneryexample;

import com.ashindigo.spinneryexample.screen.SampleChestScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class SpinneryExampleClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(SpinneryExample.sampleChestHandler, SampleChestScreen::new);
    }
}
