package org.bluk.avmeister.skins.generator.queue;

import io.minio.ObjectWriteResponse;
import net.imglib2.img.Img;
import net.imglib2.img.array.ArrayImgFactory;
import net.imglib2.img.cell.CellImgFactory;
import net.imglib2.type.numeric.integer.UnsignedByteType;
import net.imglib2.type.numeric.real.FloatType;
import org.bluk.avmeister.Avmeister;
import org.bluk.avmeister.abstracts.queue.AbstractQueueTask;
import org.bluk.avmeister.abstracts.queue.QueueTaskStatus;
import org.bluk.avmeister.config.ConfigValues;
import org.bluk.avmeister.skins.CompleteSkin;
import org.bluk.avmeister.skins.SkinTexture;
import org.bluk.avmeister.skins.generator.ImageUploader;
import org.bluk.avmeister.skins.generator.SkinGenerator;
import org.bluk.avmeister.skins.parts.SkinPart;
import org.bluk.avmeister.utils.SkinCacher;
import org.bluk.avmeister.utils.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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
        long startTime = System.currentTimeMillis();

        // Processing this skin
        BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

        // Settings everything to transparent
        Graphics2D g2d = image.createGraphics();

        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, 64, 64);
        g2d.dispose();

        // Drawing skin parts on this image
        for (SkinPart part : skin.parts) {
            // Opening this part's image file
            try {
                var file = new File(part.fullTexturePath);
                var partImage = ImageIO.read(file);

                for (int x = part.x; x < partImage.getWidth(); x++) {
                    for (int y = part.y; y < partImage.getHeight(); y++) {
                        var pixelColor = new Color(partImage.getRGB(x, y), true);

                        if (pixelColor.getAlpha() != 0) {
                            image.setRGB(x, y, pixelColor.getRGB());
                        }
                    }
                }
            } catch (Throwable e) {
                // @todo normal error handling
                e.printStackTrace();

                this.setStatus(QueueTaskStatus.FULFILLED);
                return;
            }
        }

        // Saving this image to /temp folder
        // @todo delete previous file
        var skinPath = Avmeister.instance.getDataFolder() + "/temp/file.png";
        try {
            ImageIO.write(image, "png", new File(skinPath));
        } catch (Throwable e) {
            // @todo normal errors
            e.printStackTrace();

            this.setStatus(QueueTaskStatus.FULFILLED);
            return;
        }

        Avmeister.instance.getLogger().info(String.format("Image generation done in %s", Time.millisToShortDHMS(System.currentTimeMillis() - startTime)));

        // Uploading this skin
        try {
            startTime = System.currentTimeMillis();

            var object = ImageUploader.uploadFrom(skinPath, skin.hash + ".png");

            Avmeister.instance.getLogger().info(String.format("Image uploaded in %s", Time.millisToShortDHMS(System.currentTimeMillis() - startTime)));

            startTime = System.currentTimeMillis();

            // Asking SkinsRestorer to generate new skin
            String skinUrl = String.format(ConfigValues.storageConfiguration.endpoint + "/%s/%s", ConfigValues.storageConfiguration.bucketName, skin.hash + ".png");
            var skinProps = Avmeister.skinRestorer.genSkinUrl(skinUrl, null);

            Avmeister.instance.getLogger().info(String.format("Skin generation done in %s", Time.millisToShortDHMS(System.currentTimeMillis() - startTime)));

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
