package org.bluk.avmeister.skins.generator;

import io.minio.MinioClient;

public class ImageUploader {
    public static MinioClient CLIENT = MinioClient
            .builder()
            // @todo
            // make this values configurable
            .endpoint("https://play.min.io")
            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
            .build();
}
