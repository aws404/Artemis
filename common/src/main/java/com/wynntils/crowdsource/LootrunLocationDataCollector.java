/*
 * Copyright © Wynntils 2023.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.crowdsource;

import com.wynntils.core.components.Models;
import com.wynntils.core.crowdsource.CrowdSourcedDataCollector;
import com.wynntils.core.crowdsource.datatype.LootrunTaskLocation;
import com.wynntils.core.crowdsource.type.CrowdSourcedDataType;
import com.wynntils.models.beacons.type.Beacon;
import com.wynntils.models.lootrun.event.LootrunBeaconSelectedEvent;
import com.wynntils.models.lootrun.type.LootrunLocation;
import com.wynntils.models.lootrun.type.LootrunTaskType;
import java.util.Optional;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LootrunLocationDataCollector extends CrowdSourcedDataCollector<LootrunTaskLocation> {
    @SubscribeEvent
    public void onLootrunTaskSelected(LootrunBeaconSelectedEvent event) {
        Beacon beacon = event.getBeacon();

        if (!beacon.color().isUsedInLootruns()) return;

        Optional<LootrunTaskType> currentTaskTypeOpt = Models.Lootrun.getTaskType();
        if (currentTaskTypeOpt.isEmpty()) return;

        Optional<LootrunLocation> currentLocationOpt = Models.Lootrun.getLocation();
        if (currentLocationOpt.isEmpty()) return;

        collect(new LootrunTaskLocation(
                currentLocationOpt.get(),
                currentTaskTypeOpt.get(),
                event.getTaskLocation().location()));
    }

    @Override
    protected CrowdSourcedDataType getDataType() {
        return CrowdSourcedDataType.LOOTRUN_TASK_LOCATIONS;
    }
}
