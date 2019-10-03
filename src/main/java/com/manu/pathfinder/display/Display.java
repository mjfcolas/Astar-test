package com.manu.pathfinder.display;

import com.manu.pathfinder.grid.Grid;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Display extends Application {

    private Group root = new Group();
    private Grid grid = new Grid();
    private DisplayedGrid displayedGrid = new DisplayedGrid(grid);
    private ControlPanel controlPanel = new ControlPanel(grid);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        grid.subscribe(displayedGrid);
        root.getChildren().add(displayedGrid);
        root.getChildren().add(controlPanel);

        Scene scene = new Scene(root, 900, 600, Color.LIGHTBLUE);
        primaryStage.setScene(scene);

        primaryStage.show();

        grid.init();
    }
}
