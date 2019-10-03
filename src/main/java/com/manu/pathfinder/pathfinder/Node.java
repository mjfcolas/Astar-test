package com.manu.pathfinder.pathfinder;

import java.util.Set;

public abstract class Node {

    public abstract Set<Node> getNeighbours();

    public abstract int getH(Node destNode);

    public abstract int getPriceToGo();

}
