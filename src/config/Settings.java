package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class Settings {

    private static final String SETTINGS_FILE = "settings.properties";

    private static Settings instance;

    private Integer rows;
    private Integer columns;
    private Integer appleCount;

    private Settings() {
        if (instance != null) {
            throw new RuntimeException("This class is a singleton!");
        }
        instance = this;
        var properties = new Properties();
        try (var inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
            String rowsProp = properties.getProperty("rows");
            String columnsProp = properties.getProperty("columns");
            String appleCountProp = properties.getProperty("appleCount");
            rows = Integer.valueOf(rowsProp);
            columns = Integer.valueOf(columnsProp);
            appleCount = Integer.valueOf(appleCountProp);
        } catch (IOException  e) {
            System.out.println("Cannot read properties file.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid properties.");
        }
    }

    public Optional<Integer> getRows() {
        return Optional.ofNullable(rows);
    }

    public Optional<Integer> getColumns() {
        return Optional.ofNullable(columns);
    }

    public Optional<Integer> getAppleCount() {
        return Optional.ofNullable(appleCount);
    }

    public static Settings getInstance() {
        if (instance == null) return new Settings();
        return instance;
    }
}
