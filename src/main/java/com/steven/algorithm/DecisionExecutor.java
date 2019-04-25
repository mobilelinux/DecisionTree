package com.steven.algorithm;

/**
 * DecisionTree Demo
 *
 * @author steven
 */
import static com.steven.algorithm.DecisionTree.Direct.RIGHT;
import static com.steven.algorithm.DecisionTree.ProcessResult;

public class DecisionExecutor {
    static class MyProcessResult extends ProcessResult<String> {
        StringBuilder sb = new StringBuilder();
        
        @Override
        String getResult() {
            return sb.toString();
        }
        
        @Override
        void processResult(String data) {
            sb.append("process:" + data);
        }
    }
    
    public static void main(String[] args) {
        MyProcessResult result = new MyProcessResult();
        DecisionTree.execute(
            new DecisionTree()
                .addLeft(
                    new DecisionTree("left1")
                        .setD(() -> RIGHT)
                        .setP((DecisionTree dt, ProcessResult sb1) -> sb1.processResult(dt.getName()))
                        .addLeft(new DecisionTree("l-l"))
                        .addRight(new DecisionTree("l-r")))
                .addRight(
                    new DecisionTree("right1")
                        .addLeft(new DecisionTree("r-l"))
                        .addRight(new DecisionTree("r-r"))),
            result);
    
        System.out.println(result.getResult());
    }
}
