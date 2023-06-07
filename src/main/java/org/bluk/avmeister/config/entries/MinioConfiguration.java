package org.bluk.avmeister.config.entries;

import org.bluk.avmeister.Avmeister;

public class MinioConfiguration {
    public final String endpoint;
    public final String accessKey;
    public final String secretKey;
    public final String bucketName;

    public MinioConfiguration(String endpoint, String accessKey, String secretKey, String bucketName) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.bucketName = bucketName;
    }

    public static class Parser {
        public static MinioConfiguration parseFromConfig() {
            var section = Avmeister.instance.getConfig().getConfigurationSection("storage");

            if (section != null && (
                    !section.contains("endpoint") ||
                            !section.contains("accessKey") ||
                            !section.contains("secretKey") ||
                            !section.contains("bucketName")
            )) {
                // Getting values from config
                return new MinioConfiguration(
                        section.getString("endpoint"),
                        section.getString("accessKey"),
                        section.getString("secretKey"),
                        section.getString("bucketName")
                );
            } else {
                // Default configuration values
                return new MinioConfiguration(
                        "https://assets.k8s.odzi.dog",
                        "avmeister-public",
                        "9mRoGRqu&dBMYKAo*vR@WSYPuri",
                        "avmeister-public"
                );
            }
        }
    }
}
