package com.manu.pathfinder.pathfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PathFinder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathFinder.class.getName());

    private Map<Node, NodeStatus> openSet = new HashMap<>();
    private Map<Node, NodeStatus> closeSet = new HashMap<>();

    private NodeStatus currentNode;
    private Map<Node, NodeStatus> nodeStatuses = new HashMap<>();

    public List<Node> findPath(Node start, Node goal) {
        long startTime = System.currentTimeMillis();
        LOGGER.trace("PathFinder.findPath");

        nodeStatuses.putIfAbsent(start, new NodeStatus(start));
        this.openSet.put(start, nodeStatuses.get(start));
        nodeStatuses.get(start).setG(0);

        while (!openSet.isEmpty()) {
            LOGGER.trace("OpenSet size : {}", this.openSet.size());
            Optional<NodeStatus> optionalCurrentNode = this.getLowestDistanceNode(goal);
            currentNode = null;

            if (optionalCurrentNode.isPresent()) {
                currentNode = optionalCurrentNode.get();
            } else {
                break;
            }
            if (currentNode.getUnderlyingNode().equals(goal)) {
                break;
            }

            LOGGER.trace("currentNode : {} - distance : {}", currentNode.getUnderlyingNode().toString(), currentNode.getF(goal));

            Set<Node> successors = currentNode.getUnderlyingNode().getNeighbours();
            for (Node successor : successors) {
                this.processSuccesorNode(successor);
            }
            this.closeSet.put(currentNode.getUnderlyingNode(), currentNode);
        }

        List<Node> path = new ArrayList<>();
        path.add(goal);
        Node current = nodeStatuses.get(goal).getParent();
        while (current != null) {
            path.add(current);
            current = nodeStatuses.get(current).getParent();
        }

        LOGGER.trace("TIME : {}", System.currentTimeMillis() - startTime);

        return path;
    }

    private void processSuccesorNode(Node successor) {
        int priceToGo = successor.getPriceToGo();
        nodeStatuses.putIfAbsent(successor, new NodeStatus(successor));
        nodeStatuses.get(successor).setG(currentNode.getG() + priceToGo);
        NodeStatus nodeInOpenSet = this.findNodeInOpenSet(successor);
        if (nodeInOpenSet != null && nodeStatuses.get(successor).getG() >= nodeInOpenSet.getG()) {
            return;
        }
        NodeStatus nodeInClosedSet = this.findNodeInClosedSet(successor);
        if (nodeInClosedSet != null && nodeStatuses.get(successor).getG() >= nodeInClosedSet.getG()) {
            return;
        }

        closeSet.remove(nodeStatuses.get(successor));
        nodeStatuses.get(successor).setParent(currentNode.getUnderlyingNode());

        LOGGER.trace("Add {} in open set", successor);
        openSet.put(successor, nodeStatuses.get(successor));
    }

    private Optional<NodeStatus> getLowestDistanceNode(Node goal) {
        return this.openSet.values().stream().filter(x -> !this.closeSet.containsKey(x.getUnderlyingNode())).min(Comparator.comparing(x -> x.getF(goal)));
    }

    private NodeStatus findNodeInOpenSet(Node cell) {
        return this.openSet.get(cell);

    }

    private NodeStatus findNodeInClosedSet(Node cell) {
        return this.closeSet.get(cell);

    }

}
