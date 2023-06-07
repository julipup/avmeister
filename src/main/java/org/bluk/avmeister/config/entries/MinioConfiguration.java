package org.bluk.avmeister.config.entries;

import org.bluk.avmeister.Avmeister;

public class MinioConfiguration {
    public String endpoint;
    public String accessKey;
    public String secretKey;
    public String bucketName;

    public static class Parser {
        public static MinioConfiguration parseFromConfig() {
            var section = Avmeister.instance.getConfig().getConfigurationSection("storage");
            var configInstance = new MinioConfiguration();

            if (section != null && (
                    !section.contains("endpoint") ||
                            !section.contains("accessKey") ||
                            !section.contains("secretKey") ||
                            !section.contains("bucketName")
            )) {
                // Getting values from config
                configInstance.endpoint = section.getString("endpoint");
                configInstance.accessKey = section.getString("accessKey");
                configInstance.secretKey = section.getString("secretKey");
                configInstance.bucketName = section.getString("bucketName");
            } else {
                // Default configuration values
                configInstance.endpoint = "https://play.min.io";
                configInstance.accessKey = "Q3AM3UQ867SPQQA43P2F";
                configInstance.secretKey = "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG";
                configInstance.bucketName = "avmeister-public";
            }

            return configInstance;
        }
    }
}
