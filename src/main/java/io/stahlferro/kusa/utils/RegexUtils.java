package io.stahlferro.kusa.utils;

public class RegexUtils {
    public static final String uuidRgx = "([a-f0-9]{8})-([a-f0-9]{4})-([a-f0-9]{4})-([a-f0-9]{4})-([a-f0-9]{12})";
    public static final String uuidRgxQuoted = "\"" + uuidRgx + "\"";
}
