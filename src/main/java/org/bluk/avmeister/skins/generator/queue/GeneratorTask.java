package org.bluk.avmeister.skins.generator.queue;

import io.minio.ObjectWriteResponse;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.queue.AbstractQueueTask;
import org.bluk.avmeister.abstracts.queue.QueueTaskStatus;
import org.bluk.avmeister.config.ConfigValues;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.skins.SkinTexture;
import org.bluk.avmeister.skins.generator.ImageUploader;
import org.bluk.avmeister.skins.generator.SkinGenerator;
import org.bluk.avmeister.utils.SkinCacher;

public class GeneratorTask extends AbstractQueueTask {
    private final CompleteSkin skin;
    private Runnable callback;

    public GeneratorTask(CompleteSkin skinToProcess) {
        super(SkinGenerator.getQueue());
        this.skin = skinToProcess;
    }

    public GeneratorTask(CompleteSkin skinToProcess, Runnable callback) {
        super(SkinGenerator.getQueue());
        this.skin = skinToProcess;
        this.callback = callback;
    }

    @Override
    public void handler() {
        // Processing this skin
        Avmeister.instance.getLogger().info(String.format("Processing skin: %s", skin.toString()));

        // @todo
        // Drawing all SkinParts on this skin

        // Uploading this skin
        var skinPath = Avmeister.instance.getDataFolder() + "/temp/test-skin.png";
        ObjectWriteResponse object;

        try {
            object = ImageUploader.uploadFrom(skinPath, skin.hash + ".png");

            Avmeister.instance.getLogger().info(String.format("Object uploaded: %s", object.etag()));

            // Asking SkinsRestorer to generate new skin
            String skinUrl = String.format(ConfigValues.storageConfiguration.endpoint + "/%s/%s", ConfigValues.storageConfiguration.bucketName, skin.hash + ".png");

            Avmeister.instance.getLogger().info(String.format("Asking SkinRestorer to generate skin from %s", skinUrl));
            var skinProps = Avmeister.skinRestorer.genSkinUrl(skinUrl, null);

            // Storing this skin in SkinsRestorer
            Avmeister.skinRestorer.setSkinData(skin.hash, Avmeister.skinRestorer.createPlatformProperty("textures", skinProps.getValue(), skinProps.getSignature()), 0);

            skin.texture = new SkinTexture(skinProps.getValue(), skinProps.getSignature());

            // Caching this skin information
            SkinCacher.saveToCache(skin.hash, skin.texture);

            // Running callback
            if (this.callback != null) this.callback.run();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // Marking this task as fulfilled
        this.setStatus(QueueTaskStatus.FULFILLED);
    }
}
