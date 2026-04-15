package com.example.decathlon.service;

public class DecathlonCalculator {

    public static int calculate(String discipline, Double value) {
        if (value == null || value <= 0) return 0;

        double a, b, c;
        boolean isTrackEvent = false;
        double resultValue = value;

        switch (discipline.toLowerCase().replace(" ", "")) {
            case "100m" -> {
                a = 25.4347;
                b = 18;
                c = 1.81;
                isTrackEvent = true;
            }
            case "kaugushüpe" -> {
                a = 0.14354;
                b = 220;
                c = 1.4;
                resultValue = value * 100;
            }
            case "kuulitõuge" -> {
                a = 51.39;
                b = 1.5;
                c = 1.05;
            }
            case "kõrgushüpe" -> {
                a = 0.8465;
                b = 75;
                c = 1.42;
                resultValue = value * 100;
            }
            case "400m" -> {
                a = 1.53775;
                b = 82;
                c = 1.81;
                isTrackEvent = true;
            }
            case "110mtõkked" -> {
                a = 5.74352;
                b = 28.5;
                c = 1.92;
                isTrackEvent = true;
            }
            case "kettaheide" -> {
                a = 12.91;
                b = 4;
                c = 1.1;
            }
            case "teivashüpe" -> {
                a = 0.2797;
                b = 100;
                c = 1.35;
                resultValue = value * 100;
            }
            case "odavise" -> {
                a = 10.14;
                b = 7;
                c = 1.08;
            }
            case "1500m" -> {
                a = 0.03768;
                b = 480;
                c = 1.85;
                isTrackEvent = true;
            }
            default -> {
                return 0;
            }
        }

        double points;
        if (isTrackEvent) {
            //jooksualadel peab tulemus olema väiksem kui konstant b
            if (resultValue >= b) return 0;
            points = a * Math.pow((b - resultValue), c);
        } else {
            //väljakualadel suurem kui b
            if (resultValue <= b) return 0;
            points = a * Math.pow((resultValue - b), c);
        }

        return (int) Math.floor(points);
    }
}
