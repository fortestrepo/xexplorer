package com.utilitysoftwareservices.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.utilitysoftwareservices.Point;

/**
 * An implementation of {@link PathFinder}.
 * 
 * The detailed implementation is based on Dijkstra's algorithm, see more: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * 
 */
public class ShortestPathFinder implements PathFinder {
    final static int EDGE_WEIGHT = 1;
    @Override
    public List<Point> find(Map<Point, UnitStatus> tableTopWithStatus, Point source, Point target) {
        Graph graph = createGraphForTableTop(tableTopWithStatus);
        Optional<Node> sourceNode = graph.find(source);
        Optional<Node> targetNode = graph.find(target);
        if (sourceNode.isPresent() && targetNode.isPresent()) {
            graph.calculateShortestPathFromSource(sourceNode.get());
            List<Node> shortestNodePath = targetNode.get().getShortestPath();
            if (shortestNodePath != null && !shortestNodePath.isEmpty()) {
                return shortestNodePath.stream().map(Node::getContent).collect(Collectors.toList());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private Graph createGraphForTableTop(Map<Point, UnitStatus> tableTopWithStatus) {
        final Graph graph = new Graph();

        // add all the nodes into graph
        tableTopWithStatus.keySet().stream().forEach(point -> {
            Node node = new Node(point);
            graph.addNode(node);
           
        });

        // link the neighbours nodes in the graph
        graph.getNodes().stream().forEach(n -> {
            Point point = n.getContent();
            Point left = Point.of(point.getX() - 1, point.getY());
            if (UnitStatus.EMPTY.equals(tableTopWithStatus.get(left))) {
                n.addDestination(graph.find(left).get(), EDGE_WEIGHT);
            }
            Point top = Point.of(point.getX(), point.getY() + 1);
            if (UnitStatus.EMPTY.equals(tableTopWithStatus.get(top))) {
                n.addDestination(graph.find(top).get(), EDGE_WEIGHT);
            }
            Point right =Point.of(point.getX() + 1, point.getY());
            if (UnitStatus.EMPTY.equals(tableTopWithStatus.get(right))) {
                n.addDestination(graph.find(right).get(), EDGE_WEIGHT);
            }
            Point bottum = Point.of(point.getX(), point.getY() - 1);
            if (UnitStatus.EMPTY.equals(tableTopWithStatus.get(bottum))) {
                n.addDestination(graph.find(bottum).get(), EDGE_WEIGHT);
            }
        });

        return graph;
    }

    /**
     * Data structure represents a graph
     * 
     */
    static class Graph {
        private Set<Node> nodes = new HashSet<>();
    
        /**
         * Calculate the shortest pathes to the source node for all rest nodes of the graph
         * 
         * @param source starting node of the graph
         */
        public void calculateShortestPathFromSource(Node source) {
    
            source.setDistance(0);
    
            Set<Node> settledNodes = new HashSet<>();
            Set<Node> unsettledNodes = new HashSet<>();
            unsettledNodes.add(source);
    
            while (unsettledNodes.size() != 0) {
                Node currentNode = getLowestDistanceNode(unsettledNodes);
                unsettledNodes.remove(currentNode);
                for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                    Node adjacentNode = adjacencyPair.getKey();
                    Integer edgeWeigh = adjacencyPair.getValue();
    
                    if (!settledNodes.contains(adjacentNode)) {
                        calculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                        unsettledNodes.add(adjacentNode);
                    }
                }
                currentNode.getShortestPath().add(currentNode);
                settledNodes.add(currentNode);
            }
        }
    
        /**
         * Find the Node by its content in the graph
         * @param content content of the node
         * @return node from graph
         */
        public Optional<Node> find(Point content) {
            return getNodes().stream().filter(n -> n.getContent().equals(content)).findFirst();
        }
    
        private void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
            Integer sourceDistance = sourceNode.getDistance();
            if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
                evaluationNode.setDistance(sourceDistance + edgeWeigh);
                LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
                shortestPath.add(sourceNode);
                evaluationNode.setShortestPath(shortestPath);
            }
        }
    
        private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
            Node lowestDistanceNode = null;
            int lowestDistance = Integer.MAX_VALUE;
            for (Node node : unsettledNodes) {
                int nodeDistance = node.getDistance();
                if (nodeDistance < lowestDistance) {
                    lowestDistance = nodeDistance;
                    lowestDistanceNode = node;
                }
            }
            return lowestDistanceNode;
        }
    
        /**
         * add node to the graph
         * 
         * @param node to be added
         */
        public void addNode(Node node) {
            nodes.add(node);
        }
    
        /**
         * Getter of nodes field
         * 
         * @return all nodes in the Graph
         */
        public Set<Node> getNodes() {
            return nodes;
        }
    
    }

    /**
     * Node data structure reprsents a node in a Graph
     * 
     */
    static class Node {
        private final Point content;
    
        private List<Node> shortestPath = new LinkedList<>();
    
        private Integer distance = Integer.MAX_VALUE;
    
        private Map<Node, Integer> adjacentNodes = new HashMap<>();
    
        public Node(Point content) {
            this.content = content;
        }
    
        /**
         * Add a node to 
         * 
         * @param destination the target node
         * @param distance weight of the edge
         */
        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }
    
        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Node)) {
                return false;
            }
            Node node = (Node) o;
            return getContent().equals(node.getContent());
        }
    
        @Override
        public int hashCode() {
            return Objects.hash(getContent());
        }
    
        @Override
        public String toString() {
            return String.format("Node(%s)", getContent());
        } 
    
        public Point getContent() {
            return content;
        }
    
        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }
    
        public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
            this.adjacentNodes = adjacentNodes;
        }
    
        public Integer getDistance() {
            return distance;
        }
    
        public void setDistance(Integer distance) {
            this.distance = distance;
        }
    
        public List<Node> getShortestPath() {
            return shortestPath;
        }
    
        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }
    
    }
}