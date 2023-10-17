/*
 * Copyright © Wynntils 2022-2023.
 * This file is released under LGPLv3. See LICENSE for full license details.
 */
package com.wynntils.overlays.infobox;

import com.wynntils.core.consumers.overlays.TextOverlay;
import com.wynntils.core.persisted.Persisted;
import com.wynntils.core.persisted.config.Config;
import com.wynntils.utils.render.type.HorizontalAlignment;

public class InfoBoxOverlay extends TextOverlay {
    @Persisted
    public final Config<String> content = new Config<>("");

    @Persisted
    public final Config<HorizontalAlignment> alignment = new Config<>(HorizontalAlignment.LEFT);

    public InfoBoxOverlay(int id) {
        super(id);
    }

    @Override
    public String getTemplate() {
        return content.get();
    }

    @Override
    public String getPreviewTemplate() {
        if (!content.get().isEmpty()) {
            return content.get();
        }

        return "&cX: {x(my_loc):0}, &9Y: {y(my_loc):0}, &aZ: {z(my_loc):0}";
    }

    @Override
    public HorizontalAlignment getRenderHorizontalAlignment() {
        return alignment.get();
    }
}
