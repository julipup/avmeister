package org.bluk.avmeister.managers.playermanager.actions;

import net.skinsrestorer.api.PlayerWrapper;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.managers.playermanager.ManagedPlayer;

public class SkinUpdater {
    private final ManagedPlayer player;

    public SkinUpdater(ManagedPlayer managedPlayer) {
        this.player = managedPlayer;
    }

    //
    // Actions
    public void setSkin() {
        var VALUE = "ewogICJ0aW1lc3RhbXAiIDogMTY0MTIwNjc4OTIwNywKICAicHJvZmlsZUlkIiA6ICJjZjgwY2E3NDFjNWQ0N2E3YWFjNGNmYjI2MjI0NDJmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJzb21lb25lX28iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc4NGJiOTEwMDQ4MGVlMTMyNWIyY2Q4NWJkYTkxMjI1NDcwYWMwOTRlZTExNzRiMzg4MDdmNzAwZDcyZDJkYyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";
        var SIGNATURE = "P2+tca61qcDIdKmIUgENZ0bhGzq3Y7mlGrBNpqVTMXGem8A8dBv7JaUqJqdwdFDhQOn9VExiUbPWQLbTc/OQezXxonFw2Wwq7wK1lRGPUwZIpLQxPh9JgkVPBib/vG/wgGm7qMscvkRp06vhQB1OdtFEKnPwt5T6GLfCnP5ifLPaWo9FCdr5bgO7RaozXS4hgGLjt1y87JAWZMABWuFQGPeNgnDQAlSVQTKNYosxjyl51wwDZxhHnjmW1UUqZZehQ2NlQ2G/bdp2sasf/8aWfWkLNifY01c7pNGDAtVPes5C0xAjHnCjNpiId/ylKYeb0HCM3w18N5kWPo2LULHb4R7TVgXuHBoIYHr70zx1DSutNLchh5NmTp/FhRZgkP6sucBVu6Cq1g4RP11B7vkQRZJbjAl6r0ur7pRha+ZFI6hR+k8NNqSWozree5oR7xZ7gaSKARcD9i78YNRXbDRprastLWV3iwH2SEeEV2JmgDXN+CjM6HJ0liXfz7VtRKajG8zF/9ZH3RxegbRxiqzs+CUkJnHtxKuDYjfScW6uFflvh8/Wf//xEulzxEgdAZdXzBgwPv3U8uXgfN1qHP0SAVaivZPL5g7e0hDTdrXFbUA6+n6PTssuwf52gLGdMaHJ0AOdrlgXxDSFb7LXEg+bWv8lFs34SlVFyCmZFEOLvZU=";

        try {
            Avmeister.skinRestorer.setSkinData("custom", Avmeister.skinRestorer.createPlatformProperty("textures", VALUE, SIGNATURE));
            Avmeister.skinRestorer.setSkin(player.getPlayer().getName(), "custom");
            Avmeister.skinRestorer.applySkin(new PlayerWrapper(player.getPlayer()));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ;
    }
}
