DecisionTree
========

An simple implementation of DecisionTree
----------------------
 - How to Use
 
```java
    MyProcessResult result = new MyProcessResult();
    DecisionTree dt = new DecisionTree()
                        .addLeft
                            new DecisionTree()
                                .setD(() -> RIGHT)
                                .setP((DecisionTree dt, ProcessResult sb1) -> sb1.processResult(dt.getName()))
                                .addLeft(new DecisionTree())
                                .addRight(new DecisionTree()));
    DecisionTree.execute(dt, result);
```