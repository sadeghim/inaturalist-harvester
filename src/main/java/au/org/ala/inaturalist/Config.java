package au.org.ala.inaturalist;


import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Config {

    public final String GRANT_TYPE;
    public final String EOL_FIELDS;
    public final String DWC_FIELDS;
    public final int PAGE_SIZE;
    public final String MONGODB_CONNECTION;
    public final int THREAD_COUNT;
    public final String API_BASE_URL;
    public final String CSV_EXPORT_DIR;
    public final String TEMP_DB_DIR;
    public final String USERNAME;
    public final String PASSWORD;
    public final String APP_ID;
    public final String APP_SECRET;

    public Config(String filePath) throws ConfigurationException {

        Configurations configs = new Configurations();
        PropertiesConfiguration config = configs.properties(new File(filePath));

        PAGE_SIZE = config.getInt("page_size");
        MONGODB_CONNECTION = config.getString("mongodb_connection");
        THREAD_COUNT = config.getInt("thread_count");
        API_BASE_URL = config.getString("inat.api_base_url");
        TEMP_DB_DIR = config.getString("temp_db_dir");
        CSV_EXPORT_DIR = config.getString("csv_export_dir");
        EOL_FIELDS = config.getString("eol_fields");
        DWC_FIELDS = config.getString("dwc_fields");
        APP_ID = config.getString("inat.app_id");
        APP_SECRET = config.getString("inat.app_secret");
        GRANT_TYPE = config.getString("inat.grant_type");
        USERNAME = config.getString("inat.username");
        PASSWORD = config.getString("inat.password");
    }
}
