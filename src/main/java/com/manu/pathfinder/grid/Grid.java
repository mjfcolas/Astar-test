package com.manu.pathfinder.grid;

import com.manu.pathfinder.pathfinder.Node;
import com.manu.pathfinder.pathfinder.PathFinder;

import javax.swing.event.EventListenerList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    private final Integer height = 21;
    private final Integer width = 21;
    private final Map<Coordonate, Cell> gridCells = new HashMap<>();

    private final EventListenerList listeners = new EventListenerList();

    public void init() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Coordonate current = new Coordonate(i, j);
                int priceToGo = 1;
                Cell created = new Cell(current, priceToGo);
                gridCells.put(current, created);
                this.fireCellCreated(created);
            }
        }

        for (Cell current : gridCells.values()) {
            current.initializeNeighbours(gridCells);
        }
    }

    public void subscribe(final GridEventListener listener) {
        this.listeners.add(GridEventListener.class, listener);
    }

    public void findPath(Coordonate startCoord, Coordonate endCoord) {
        PathFinder pathFinder = new PathFinder();
        Cell start = gridCells.get(startCoord);
        Cell goal = gridCells.get(endCoord);
        List<Node> path = pathFinder.findPath(start, goal);
        for (Cell cell : gridCells.values()) {
            cell.setHighlighted(false);
            this.fireCellModified(cell);
        }
        for (Node current : path) {
            Cell cell = (Cell) current;
            cell.setHighlighted(true);
            this.fireCellModified(cell);
        }
    }

    public void changeCellPrice(Coordonate coord){
        this.gridCells.get(coord).changePriceToGo();
        this.fireCellModified(this.gridCells.get(coord));
    }

    private void fireCellCreated(Cell created) {
        for (final GridEventListener listener : this.listeners.getListeners(GridEventListener.class)) {
            listener.cellCreated(created);
        }
    }

    private void fireCellModified(Cell modified) {
        for (final GridEventListener listener : this.listeners.getListeners(GridEventListener.class)) {
            listener.cellModified(modified);
        }
    }
}
