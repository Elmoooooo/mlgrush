package de.ips.mlgrush.storage.config;

import com.google.gson.annotations.SerializedName;
import de.ips.mlgrush.MLGRushPlugin;
import lombok.Data;

import java.util.function.Supplier;

@Data
public class DatabaseConfig implements Config {

    @SerializedName(value = "storageSystem")
    private String storageSystem = "file";

    @SerializedName(value = "mongodb")
    private MongoDBData mongoDBData = new MongoDBData();

    @SerializedName(value = "mysql")
    private MySQLData mySQLData = new MySQLData();

    @SerializedName(value = "fileStorage")
    private FileStorageData fileStorageData = new FileStorageData();

    @Data
    public static class MongoDBData {
        @SerializedName(value = "hostname")
        private String host = "127.0.0.1";
        @SerializedName(value = "port")
        private int port = 27017;

        @SerializedName(value = "user")
        private String username = "admin";

        @SerializedName(value = "password")
        private String password = "admin123";

        @SerializedName(value = "userDb")
        private String userDb = "admin";

        @SerializedName(value = "storageDb")
        private String storageDb = "mlgrush";

        @SerializedName(value = "storageCollection")
        private String storageCollection = "mlgrush";

        @SerializedName(value = "authEnabled")
        private boolean authEnabled;
    }

    @Data
    public static class MySQLData {
        @SerializedName(value = "hostname")
        private String host = "127.0.0.1";
        @SerializedName(value = "port")
        private int port = 3306;

        @SerializedName(value = "user")
        private String user = "root";

        @SerializedName(value = "password")
        private String password = "admin123";

        @SerializedName(value = "storageDb")
        private String storageDb = "mlgrush";
    }

    @Data
    public static class FileStorageData {
        @SerializedName(value = "name")
        private String fileName = "playerData";
    }

    public static final Supplier<String> DEFAULT_JSON = () ->
            MLGRushPlugin.getInstance()
                    .getGson()
                    .toJson(new DatabaseConfig());

}
