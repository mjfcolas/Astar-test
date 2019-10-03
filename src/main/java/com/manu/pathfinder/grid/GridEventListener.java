package com.manu.pathfinder.grid;

import java.util.EventListener;

public interface GridEventListener extends EventListener {

    void cellCreated(Cell created);
    void cellModified(Cell modified);
}
