package com.manu.pathfinder.grid;

import com.manu.pathfinder.pathfinder.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Cell extends Node {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cell.class.getName());

    private final Coordonate coordonate;
    private final Set<Node> neighbours = new HashSet<>();
    private boolean highlighted = false;
    private int priceToGo;

    public Cell(final Coordonate coordonate, int priceToGo) {
        this.coordonate = coordonate;
        this.priceToGo = priceToGo;
    }

    public void changePriceToGo(){
        priceToGo++;
        if(priceToGo > 5){
            priceToGo = 1;
        }
    }

    public void initializeNeighbours(Map<Coordonate, Cell> candidates) {
        List<Coordonate> potentialNeighboursInternal = this.getCoordonate().getNeighbours();
        for (Coordonate current : potentialNeighboursInternal) {
            if (candidates.get(current) != null) {
                neighbours.add(candidates.get(current));
            }
        }
    }

    @Override
    public int getPriceToGo() {
        return priceToGo;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(final boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public Set<Node> getNeighbours() {
        return neighbours;
    }

    public Coordonate getCoordonate() {
        return coordonate;
    }

    @Override
    public int getH(final Node destNode) {
        Cell destCell = (Cell) destNode;
        int h = this.coordonate.distanceToCoordonate(destCell.getCoordonate());
        LOGGER.trace("Cell.getH - coord : {} h :{}", this.toString(), h);
        return h;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cell cell = (Cell) o;
        return Objects.equals(coordonate, cell.coordonate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonate);
    }

    @Override
    public String toString() {
        return coordonate.getX() + " " + coordonate.getY();
    }
}
