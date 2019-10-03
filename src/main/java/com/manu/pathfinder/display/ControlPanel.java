package com.manu.pathfinder.display;

import com.manu.pathfinder.grid.Coordonate;
import com.manu.pathfinder.grid.Grid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ControlPanel extends Parent {

    private Grid grid;

    public ControlPanel(Grid grid) {
        this.grid = grid;
        this.init();
    }

    private void init() {
        Rectangle background = new Rectangle();
        background.setWidth(200);
        background.setHeight(600);
        background.setArcHeight(0);
        background.setArcWidth(0);
        background.setFill(Color.LIGHTBLUE);
        this.getChildren().add(background);
        this.setTranslateX(700);

        GridPane container = new GridPane();
        container.setPadding(new Insets(10, 10, 10, 10));
        container.setVgap(5);
        container.setHgap(5);

        TextField xOrig = new TextField();
        GridPane.setConstraints(xOrig, 0, 1);
        container.getChildren().add(xOrig);

        TextField yOrig = new TextField();
        GridPane.setConstraints(yOrig, 0, 2);
        container.getChildren().add(yOrig);

        TextField xDest = new TextField();
        GridPane.setConstraints(xDest, 0, 3);
        container.getChildren().add(xDest);

        TextField yDest = new TextField();
        GridPane.setConstraints(yDest, 0, 4);
        container.getChildren().add(yDest);

        Button button = new Button("Find Path");
        GridPane.setConstraints(button, 0, 5);
        container.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int xOrigInt = Integer.parseInt(xOrig.getText());
                int yOrigInt = Integer.parseInt(yOrig.getText());
                int xDestInt = Integer.parseInt(xDest.getText());
                int yDestInt = Integer.parseInt(yDest.getText());
                Coordonate start = new Coordonate(xOrigInt, yOrigInt);
                Coordonate end = new Coordonate(xDestInt, yDestInt);
                grid.findPath(start, end);
            }
        });

        this.getChildren().add(container);
    }
}
