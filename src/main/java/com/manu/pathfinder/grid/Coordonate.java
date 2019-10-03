package com.manu.pathfinder.grid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Coordonate {
    private final Integer x;
    private final Integer y;

    public Coordonate(final Integer x, final Integer y) {
        this.x = x;
        this.y = y;
    }

    public List<Coordonate> getNeighbours() {
        return Arrays.asList(
                new Coordonate(x + 1, y),//R
                new Coordonate(y % 2 == 0 ? x : x + 1, y - 1), //UR
                new Coordonate(y % 2 == 0 ? x - 1 : x, y - 1), //UL
                new Coordonate(x - 1, y), //L
                new Coordonate(y % 2 == 0 ? x - 1 : x, y + 1), //DL
                new Coordonate(y % 2 == 0 ? x : x + 1, y + 1)//DR
        );
    }

    public int distanceToCoordonate(Coordonate otherCoord) {
        return Coordonate.distance(this, otherCoord);
    }

    public static int distance(Coordonate a, Coordonate b) {
        return Math.max(Math.max(
                Math.abs(a.getxC() - b.getxC()),
                Math.abs(a.getyC() - b.getyC())),
                Math.abs(a.getzC() - b.getzC()));
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getxC() {
        return this.x - (this.y - (this.y & 1)) / 2;
    }

    public Integer getyC() {
        return -this.getxC() - this.getzC();
    }

    public Integer getzC() {
        return this.y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordonate that = (Coordonate) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
