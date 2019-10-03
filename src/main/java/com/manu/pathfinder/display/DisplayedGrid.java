package com.manu.pathfinder.display;

import com.manu.pathfinder.grid.Cell;
import com.manu.pathfinder.grid.Coordonate;
import com.manu.pathfinder.grid.Grid;
import com.manu.pathfinder.grid.GridEventListener;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;

import java.util.HashMap;
import java.util.Map;

public class DisplayedGrid extends Parent implements GridEventListener {

    private Grid grid;

    private Map<Coordonate, Hexagon> cells = new HashMap<>();
    private Map<Coordonate, Polygon> fxCells = new HashMap<>();

    public DisplayedGrid(Grid grid){
        this.grid = grid;
    }

    @Override
    public void cellCreated(final Cell created) {
        Polygon polygon = new Polygon();
        Hexagon hexagon = new Hexagon(created.getCoordonate(), new Double(20));

        this.modifyCell(polygon, hexagon, created);

        cells.put(created.getCoordonate(), hexagon);
        fxCells.put(created.getCoordonate(), polygon);
        this.getChildren().add(polygon);
        polygon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                grid.changeCellPrice(created.getCoordonate());
            }
        });
    }

    @Override
    public void cellModified(final Cell modified) {
        Polygon polygon = fxCells.get(modified.getCoordonate());
        Hexagon hexagon = cells.get(modified.getCoordonate());
        this.modifyCell(polygon, hexagon, modified);
    }

    private void modifyCell(Polygon polygon, Hexagon hexagon, Cell source) {
        polygon.setStrokeType(StrokeType.INSIDE);

        polygon.getPoints().addAll(hexagon.getPoints());
        switch (source.getPriceToGo()) {
            case 1:
                polygon.setFill(Color.GREEN);
                break;
            case 2:
                polygon.setFill(Color.DARKGREEN);
                break;
            case 3:
                polygon.setFill(Color.YELLOW);
                break;
            case 4:
                polygon.setFill(Color.ORANGE);
                break;
            case 5:
                polygon.setFill(Color.RED);
                break;
        }
        if (source.isHighlighted()) {
            polygon.setStrokeWidth(10);
        } else {
            polygon.setStrokeWidth(1);
        }
        polygon.setStroke(Color.BLACK);
    }
}
