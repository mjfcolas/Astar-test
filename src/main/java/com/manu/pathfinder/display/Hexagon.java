package com.manu.pathfinder.display;

import com.manu.pathfinder.grid.Coordonate;

public class Hexagon {

    private final Coordonate coordonate;
    private final Double a;
    private final Double h;

    public Hexagon(final Coordonate coordonate, Double a) {
        this.coordonate = coordonate;
        this.a = a;
        this.h = Math.sqrt(3) / 2 * a;
    }

    public Double[] getPoints() {

        Integer x = coordonate.getX();
        Integer y = coordonate.getY();

        if (y % 2 == 0) {
            return new Double[]{
                    (2 * x + 1) * h, (1.5 * y - 0.5) * a,
                    (2 * x) * h, (1.5 * y - 1) * a,
                    (2 * x - 1) * h, (1.5 * y - 0.5) * a,
                    (2 * x - 1) * h, (1.5 * y + 0.5) * a,
                    (2 * x) * h, (1.5 * y + 1) * a,
                    (2 * x + 1) * h, (1.5 * y + 0.5) * a,
            };
        } else {
            return new Double[]{
                    2 * (x+1) * h, (1.5 * y - 0.5) * a,
                    (2 * x + 1) * h, (1.5 * y - 1) * a,
                    (2 * x) * h, (1.5 * y - 0.5) * a,
                    (2 * x) * h, (1.5 * y + 0.5) * a,
                    (2 * x + 1) * h, (1.5 * y + 1) * a,
                    2 * (x+1) * h, (1.5 * y + 0.5) * a,
            };
        }
    }

}
