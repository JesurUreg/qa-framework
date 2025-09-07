package com.example.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        String env = System.getProperty("env", "config");
        String filename = env.endsWith(".properties") ? env : env + ".properties";
        InputStream in = null;
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl == null) cl = Config.class.getClassLoader();
            if (cl != null) {
                in = cl.getResourceAsStream(filename);
                if (in == null) in = cl.getResourceAsStream("/" + filename);
                if (in == null) in = cl.getResourceAsStream("config/" + filename);
                if (in == null) in = cl.getResourceAsStream("/config/" + filename);
            }
            if (in == null) throw new RuntimeException("Config file not found: " + filename);
            PROPS.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config: " + filename, e);
        } finally {
            if (in != null) try { in.close(); } catch (IOException ignored) {}
        }
    }

    public static String get(String key, String def) { return PROPS.getProperty(key, def); }
    public static String baseUrl() { return get("baseUrl", "https://demoqa.com"); }
    public static String browser() { return get("browser", "chrome"); }
    public static boolean headless() { return Boolean.parseBoolean(get("headless", "true")); }
    public static long timeoutSec() {
        String v = get("timeoutSec", "10");
        try { return Long.parseLong(v); } catch (NumberFormatException e) { return 10L; }
    }
}
