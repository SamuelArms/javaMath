/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

import static fxmllinegraph.SecantMethod.secantTableLines;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author samuel
 */
public class SecantMethod {

    static public ObservableList<TableLine> secantTableLines = FXCollections.observableArrayList();

    static private double funcOne(double x) {
        return (x - (x * x));
    }

    static private double funcTwo(double x) {
        return (Math.log(x + 1) + 1);
    }

    static private double funcThree(double x) {
        return (Math.exp(x) - (3 * x));
    }

    public static double[] SecantFunctionOne(double startingPoint, double secondStartingPoint, double decimal) {
        secantTableLines.clear();
        double[] funcOneList = new double[1000];
        double xnew, fxold1, fxold2, diff;
        double xold1 = startingPoint;
        double xold2 = secondStartingPoint;
        int iteration = 0;

        if (funcOne(xold1) * funcOne(xold2) > 0) {
            AlertBox.display("Secant Error", "This function can't be solved with secant");
            return funcOneList;
        } else {
            do {

                //determine f(xold1) and f(xold2)
                fxold1 = funcOne(xold1);
                fxold2 = funcOne(xold2);

                xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);

                funcOneList[iteration] = xnew;
                iteration += 1;

                diff = Math.abs(xnew - xold1);

                secantTableLines.add(new TableLine(iteration, xold1, xold2, fxold1, fxold2, xnew, 0.0, diff));

                xold2 = xold1;
                xold1 = xnew;

            } while (diff > decimal);
            return funcOneList;
        }
    }

    public static double[] SecantFunctionTwo(double startingPoint, double secondStartingPoint, double decimal) {
        secantTableLines.clear();
        double[] funcTwoList = new double[1000];
        double xnew, fxold1, fxold2, diff;
        double xold1 = startingPoint;
        double xold2 = secondStartingPoint;
        int iteration = 0;

        if (funcTwo(xold1) * funcTwo(xold2) > 0) {
            AlertBox.display("Secant Error", "This function can't be solved with secant");
            return funcTwoList;
        } else {
            do {

                //determine f(xold1) and f(xold2)
                fxold1 = funcTwo(xold1);
                fxold2 = funcTwo(xold2);

                xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);

                funcTwoList[iteration] = xnew;
                iteration += 1;

                diff = Math.abs(xnew - xold1);

                secantTableLines.add(new TableLine(iteration, xold1, xold2, fxold1, fxold2, xnew, 0.0, diff));

                xold2 = xold1;
                xold1 = xnew;

            } while (diff > decimal);
            return funcTwoList;
        }
    }

    public static double[] SecantFunctionThree(double startingPoint, double secondStartingPoint, double decimal) {
        secantTableLines.clear();
        double[] funcThreeList = new double[1000];
        double xnew, fxold1, fxold2, diff;
        double xold1 = startingPoint;
        double xold2 = secondStartingPoint;
        int iteration = 0;

        if (funcThree(xold1) * funcThree(xold2) > 0) {
            AlertBox.display("Secant Error", "This function can't be solved with secant");
            return funcThreeList;
        } else {
            do {

                //determine f(xold1) and f(xold2)
                fxold1 = funcThree(xold1);
                fxold2 = funcThree(xold2);

                xnew = xold1 - (fxold1 * (xold1 - xold2)) / (fxold1 - fxold2);

                funcThreeList[iteration] = xnew;
                iteration += 1;

                diff = Math.abs(xnew - xold1);

                secantTableLines.add(new TableLine(iteration, xold1, xold2, fxold1, fxold2, xnew, 0.0, diff));

                xold2 = xold1;
                xold1 = xnew;

            } while (diff > decimal);
            return funcThreeList;
        }
    }

    public static ObservableList<TableLine> getSecantLines() {
        return secantTableLines;
    }
}
