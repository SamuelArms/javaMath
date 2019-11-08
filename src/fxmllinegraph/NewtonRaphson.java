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
public class NewtonRaphson {

    static public ObservableList<TableLine> newtonTableLines = FXCollections.observableArrayList();

    public static LinkedList NewtonRaphsonFunctionOne(double startingPoint, double decimal) {
        newtonTableLines.clear();
        LinkedList funcOneList = new LinkedList();
        funcOneList.head.setElement(0.0);
        double xold, xnew, fxold, fdashold, diff;
        int iteration = 0;
        xold = startingPoint;

        do {
            iteration += 1;

            //determine f(xold) and f'(xold)
            fxold = xold - (xold * xold);
            fdashold = 1.0 - (2.0 * xold);

            xnew = xold - (fxold / fdashold);
            System.out.println("Approx for iteration " + iteration + " is " + xnew);
            Double xnewTest = xnew;
            if (xnewTest.isNaN()) {
                AlertBox.display("Newton-Raphson Error", "This function can't be solved with newton-raphson");
                while (!funcOneList.isEmpty()) {
                    funcOneList.removeFirst();
                }
                break;
            }

            diff = Math.abs(xnew - xold);

            newtonTableLines.add(new TableLine(iteration, xold, 0.0, fxold, 0.0, xnew, fdashold, diff));

            if (iteration == 1) {
                funcOneList.head.setElement(xnew);
            } else {
                funcOneList.addMid(xnew, xold);
            }

            xold = xnew;

        } while (diff > decimal);
        System.out.println("NEWTON-RAPHSON 1: root to " + decimal + " places is " + xnew);
        System.out.println("");
        return funcOneList;
    }

    public static LinkedList NewtonRaphsonFunctionTwo(double startingPoint, double decimal) {
        newtonTableLines.clear();
        LinkedList funcTwoList = new LinkedList();
        double xold, xnew, fxold, fdashold, diff;
        int iteration = 0;
        xold = startingPoint;

        do {
            iteration += 1;

            //determine f(xold) and f'(xold)
            fxold = Math.log(xold + 1) + 1;
            fdashold = 1.0 / (xold + 1.0);

            xnew = xold - (fxold / fdashold);
            System.out.println("Approx for iteration " + iteration + " is " + xnew);
            Double xnewTest = xnew;
            if (xnewTest.isNaN()) {
                AlertBox.display("Newton-Raphson Error", "This function can't be solved with newton-raphson");
                while (!funcTwoList.isEmpty()) {
                    funcTwoList.removeFirst();
                }
                break;
            }
            diff = Math.abs(xnew - xold);

            newtonTableLines.add(new TableLine(iteration, xold, 0.0, fxold, 0.0, xnew, fdashold, diff));

            if (iteration == 1) {
                funcTwoList.head.setElement(xnew);
            } else {
                funcTwoList.addMid(xnew, xold);
            }

            xold = xnew;

        } while (diff > decimal);

        System.out.println("NEWTON-RAPHSON 2: root to " + decimal + " places is " + xnew);
        System.out.println("");
        return funcTwoList;
    }

    public static LinkedList NewtonRaphsonFunctionThree(double startingPoint, double decimal) {
        newtonTableLines.clear();
        LinkedList funcThreeList = new LinkedList();
        double xold, xnew, fxold, fdashold, diff;
        int iteration = 0;
        xold = startingPoint;

        do {
            iteration += 1;

            //determine f(xold) and f'(xold)
            fxold = Math.exp(xold) - (3 * xold);
            fdashold = Math.exp(xold) - 3;

            xnew = xold - (fxold / fdashold);
            System.out.println("Approx for iteration " + iteration + " is " + xnew);
            Double xnewTest = xnew;
            if (xnewTest.isNaN()) {
                AlertBox.display("Newton-Raphson Error", "This function can't be solved with newton-raphson");
                while (!funcThreeList.isEmpty()){
                    funcThreeList.removeFirst();
                }
                break;
            }
            diff = Math.abs(xnew - xold);

            newtonTableLines.add(new TableLine(iteration, xold, 0.0, fxold, 0.0, xnew, fdashold, diff));

            if (iteration == 1) {
                funcThreeList.head.setElement(xnew);
            } else {
                funcThreeList.addMid(xnew, xold);
            }
            xold = xnew;

        } while (diff > decimal);

        System.out.println("NEWTON-RAPHSON 3: root to " + decimal + " places is " + xnew);
        System.out.println("");
        return funcThreeList;
    }

    public static ObservableList<TableLine> getNewtonLines() {
        return newtonTableLines;
    }
}
