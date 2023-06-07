package org.bluk.avmeister.skins.generator;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.bluk.avmeister.config.ConfigValues;
import org.bluk.avmeister.exceptions.uploader.UploaderException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ImageUploader {
    public static MinioClient CLIENT = MinioClient
            .builder()
            .endpoint(ConfigValues.storageConfiguration.endpoint)
            .credentials(ConfigValues.storageConfiguration.accessKey, ConfigValues.storageConfiguration.secretKey)
            .build();

    public static ObjectWriteResponse uploadFrom(String filePath, String objectName) throws IOException, UploaderException {
        try {
            return ImageUploader.CLIENT.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(ConfigValues.storageConfiguration.bucketName)
                            .object(objectName)
                            .filename(filePath)
                            .build()
            );
        } catch (IOException exception) {
            throw exception;
        } catch (Throwable exception) {
            throw new UploaderException(exception);
        }
    }
}
