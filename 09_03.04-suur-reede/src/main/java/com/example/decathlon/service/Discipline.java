package com.example.decathlon.service;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Discipline {
    HUNDRED_METERS("100m", 25.4347, 18.0, 1.81, true, false),
    LONG_JUMP("kaugushüpe", 0.14354, 220.0, 1.4, false, true),
    SHOT_PUT("kuulitõuge", 51.39, 1.5, 1.05, false, false),
    HIGH_JUMP("kõrgushüpe", 0.8465, 75.0, 1.42, false, true),
    FOUR_HUNDRED_METERS("400m", 1.53775, 82.0, 1.81, true, false),
    HUNDRED_TEN_METERS_HURDLES("110mtõkked", 5.74352, 28.5, 1.92, true, false),
    DISCUS_THROW("kettaheide", 12.91, 4.0, 1.1, false, false),
    POLE_VAULT("teivashüpe", 0.2797, 100.0, 1.35, false, true),
    JAVELIN_THROW("odavise", 10.14, 7.0, 1.08, false, false),
    FIFTEEN_HUNDRED_METERS("1500m", 0.03768, 480.0, 1.85, true, false);

    private final String key;
    private final String displayName;
    private final double a;
    private final double b;
    private final double c;
    private final boolean trackEvent;
    private final boolean cmConversion;

    private static final Map<String, Discipline> LOOKUP = new HashMap<>();

    static {
        for (Discipline d : Discipline.values()) {
            LOOKUP.put(d.key.toLowerCase(), d);
            LOOKUP.put(d.displayName.toLowerCase().replace(" ", ""), d);
        }
    }

    Discipline(String key, String displayName, double a, double b, double c, boolean trackEvent, boolean cmConversion) {
        this.key = key;
        this.displayName = displayName;
        this.a = a;
        this.b = b;
        this.c = c;
        this.trackEvent = trackEvent;
        this.cmConversion = cmConversion;
    }

    public static Discipline fromString(String text) {
        if (text == null) return null;
        String normalized = text.toLowerCase().replace(" ", "").replace("_", "");

        return LOOKUP.get(normalized);
    }

    public int calculatePoints(double value) {
        if (value <= 0) return 0;

        double resultValue = cmConversion ? value * 100 : value;

        if (trackEvent) {
            // points = a * (b - T)^c
            if (resultValue >= b) return 0;
            return (int) Math.floor(a * Math.pow(b - resultValue, c));
        } else {
            // field events: points = a * (D - b)^c
            if (resultValue <= b) return 0;
            return (int) Math.floor(a * Math.pow(resultValue - b, c));
        }
    }
}
