package com.steven.algorithm;

import static com.steven.algorithm.DecisionTree.Direct.*;

public class DecisionTree {
    private DecisionTree left, right;
    
    private String name = "";
    
    private Processor p;
    private Decision d;
    
    DecisionTree() {}
    
    DecisionTree(String nodename) {
        this.name = nodename;
    }
    
    public static <T extends ProcessResult> void execute(DecisionTree dt, T result) {
        DecisionTree node = dt;
        while (node != null && !node.isLeaf()) {
            node.process(node, result);
            if (node.decide() == LEFT) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        if (node != null) node.process(node, result);
    }
    
    public Direct decide() {
        if (d == null) {
            if (left != null) return LEFT;
            if (right != null) return RIGHT;
            return ERRORLEFT;
        } else {
            return d.decide();
        }
    }
    
    public <T extends ProcessResult> void process(DecisionTree dt, T a) {
        if (p != null) p.process(dt, a);
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public String getName() {
        return name;
    }
    
    public DecisionTree getLeft() {
        return left;
    }
    
    public DecisionTree getRight() {
        return right;
    }
    
    public DecisionTree addLeft(DecisionTree node) {
        left = node;
        return this;
    }
    
    public DecisionTree addRight(DecisionTree node) {
        right = node;
        return this;
    }
    
    public DecisionTree setD(Decision d) {
        this.d = d;
        return this;
    }
    
    public DecisionTree setP(Processor p) {
        this.p = p;
        return this;
    }
    
    enum Direct {
        LEFT,
        RIGHT,
        ERRORLEFT
    }
    
    interface Decision {
        Direct decide();
    }
    
    interface Processor<T extends ProcessResult> {
        void process(DecisionTree dt, T a);
    }
    
    public abstract static class ProcessResult<T> {
        abstract String getResult();
        
        abstract void processResult(T data);
    }
}
