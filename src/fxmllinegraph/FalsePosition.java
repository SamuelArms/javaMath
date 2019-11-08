/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author samuel
 */
public class FalsePosition {

    static public ObservableList<TableLine> falsePositionTableLines = FXCollections.observableArrayList();

    static private double funcOne(double x) {
        return (x - (x * x));
    }

    static private double funcTwo(double x) {
        return (Math.log(x + 1) + 1);
    }

    static private double funcThree(double x) {
        return (Math.exp(x) - (3 * x));
    }

    public static LinkedList falsePositionFunctionOne(double startingPoint, double secondStartingPoint, double decimal) {
        falsePositionTableLines.clear();
        LinkedList funcOneList = new LinkedList();
        double xold1, xold2, xnew, oldxnew, diff;
        int iteration = 0;
        xold1 = startingPoint;
        xold2 = secondStartingPoint;
        xnew = xold1;
        oldxnew = 0;

        if (funcOne(xold1) * funcOne(xold2) >= 0) {
            AlertBox.display("FalsePosition Error", "This function can't be solved with false position");
            return funcOneList;
        } else {
            do {
                iteration += 1;

                // Find the point that touches x axis 
                xnew = (xold1 * funcOne(xold2) - xold2 * funcOne(xold1)) / (funcOne(xold2) - funcOne(xold1));

                // Check if the above found point is root 
                if (funcOne(xnew) == 0) {
                    break;
                }

                diff = Math.abs(xnew - oldxnew);
                
                if (iteration == 1) {
                    funcOneList.head.setElement(xnew);
                } else {
                    funcOneList.addMid(xnew, oldxnew);
                }

                oldxnew = xnew;
                System.out.println("Approx for iteration " + iteration + " is " + xnew);
                
                falsePositionTableLines.add(new TableLine(iteration, xold1, xold2, funcOne(xold1), funcOne(xold2), xnew, 0.0, diff));

                // Decide the side to repeat the steps 
                if (funcOne(xnew) * funcOne(xold1) < 0) {
                    xold2 = xnew;
                } else {
                    xold1 = xnew;
                }
            } while (diff > decimal);
            System.out.println("FALSE POSITION 1: root to " + decimal + " places is " + xnew);
            System.out.println("");
            return funcOneList;
        }

    }

    public static LinkedList falsePositionFunctionTwo(double startingPoint, double secondStartingPoint, double decimal) {
        falsePositionTableLines.clear();
        LinkedList funcTwoList = new LinkedList();
        double xold1, xold2, xnew, oldxnew, diff;
        int iteration = 0;
        xold1 = startingPoint;
        xold2 = secondStartingPoint;
        xnew = xold1;
        oldxnew = 0;

        if (funcTwo(xold1) * funcTwo(xold2) >= 0) {
            AlertBox.display("FalsePosition Error", "This function can't be solved with false position");
            return funcTwoList;
        } else {
            do {
                iteration += 1;

                // Find the point that touches x axis 
                xnew = (xold1 * funcTwo(xold2) - xold2 * funcTwo(xold1)) / (funcTwo(xold2) - funcTwo(xold1));

                // Check if the above found point is root 
                if (funcTwo(xnew) == 0) {
                    break;
                }

                diff = Math.abs(xnew - oldxnew);
                
                if (iteration == 1) {
                    funcTwoList.head.setElement(xnew);
                } else {
                    funcTwoList.addMid(xnew, oldxnew);
                }

                oldxnew = xnew;
                System.out.println("Approx for iteration " + iteration + " is " + xnew);
                
                falsePositionTableLines.add(new TableLine(iteration, xold1, xold2, funcTwo(xold1), funcTwo(xold2), xnew, 0.0, diff));

                // Decide the side to repeat the steps 
                if (funcTwo(xnew) * funcTwo(xold1) < 0) {
                    xold2 = xnew;
                } else {
                    xold1 = xnew;
                }
            } while (diff > decimal);
            System.out.println("FALSE POSITION 2: root to " + decimal + " places is " + xnew);
            System.out.println("");
            return funcTwoList;

        }

    }

    public static LinkedList falsePositionFunctionThree(double startingPoint, double secondStartingPoint, double decimal) {
        falsePositionTableLines.clear();
        LinkedList funcThreeList = new LinkedList();
        double xold1, xold2, xnew, oldxnew, diff;
        int iteration = 0;
        xold1 = startingPoint;
        xold2 = secondStartingPoint;
        xnew = xold1;
        oldxnew = 0;

        if (funcThree(xold1) * funcThree(xold2) >= 0) {
            AlertBox.display("FalsePosition Error", "This function can't be solved with false position");
            return funcThreeList;
        } else {

            do {
                iteration += 1;

                // Find the point that touches x axis 
                xnew = (xold1 * funcThree(xold2) - xold2 * funcThree(xold1)) / (funcThree(xold2) - funcThree(xold1));

                // Check if the above found point is root 
                if (funcThree(xnew) == 0) {
                    break;
                }

                diff = Math.abs(xnew - oldxnew);

                if (iteration == 1) {
                    funcThreeList.head.setElement(xnew);
                } else {
                    funcThreeList.addMid(xnew, oldxnew);
                }

                oldxnew = xnew;
                System.out.println("Approx for iteration " + iteration + " is " + xnew);

                falsePositionTableLines.add(new TableLine(iteration, xold1, xold2, funcThree(xold1), funcThree(xold2), xnew, 0.0, diff));
                
                // Decide the side to repeat the steps 
                if (funcThree(xnew) * funcThree(xold1) < 0) {
                    xold2 = xnew;
                } else {
                    xold1 = xnew;
                }
            } while (diff > decimal);
            System.out.println("FALSE POSITION 3: root to " + decimal + " places is " + xnew);
            System.out.println("");
            return funcThreeList;
        }
    }
    

    public static ObservableList<TableLine> getFalsePositionLines() {
        return falsePositionTableLines;
    }
}
