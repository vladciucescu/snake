package config;

import domain.Coordinates;

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
    private Integer startRow;
    private Integer startColumn;
//    private String rows;
//    private String columns;
//    private String appleCount;
//    private String startRowProp;
//    private String startColumnProp

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
            String startRowProp = properties.getProperty("startRow");
            String startColumnProp = properties.getProperty("startColumn");
            rows = tryParseInt(rowsProp);
            columns = tryParseInt(columnsProp);
            appleCount = tryParseInt(appleCountProp);
            startRow = tryParseInt(startRowProp);
            startColumn = tryParseInt(startColumnProp);
        } catch (IOException e) {
            System.out.println("Cannot read properties file.");
        }
    }

    private Integer tryParseInt(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException e) {
            System.out.println("Invalid property value: " + string);
            return null;
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

    public Optional<Coordinates> getStartCoordinates() {
        if (startRow == null || startColumn == null) {
            return Optional.empty();
        }
        return Optional.of(new Coordinates(startRow, startColumn));
    }

    public static Settings getInstance() {
        if (instance == null) return new Settings();
        return instance;
    }
}
