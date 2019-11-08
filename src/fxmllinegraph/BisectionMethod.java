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
public class BisectionMethod {

    static public ObservableList<TableLine> bisectionTableLines = FXCollections.observableArrayList();

    static private double funcOne(double x) {
        return (x - (x * x));
    }

    static private double funcTwo(double x) {
        return (Math.log(x + 1) + 1);
    }

    static private double funcThree(double x) {
        return (Math.exp(x) - (3 * x));
    }

    public static double[] bisectFunctionOne(double startingPoint, double secondStartingPoint, double decimal) {
        bisectionTableLines.clear();
        double[] funcOneList = new double[1000];
        double xnew, fxlower, fxupper, fxnew, diff;
        double xlower = startingPoint;
        double xupper = secondStartingPoint;
        int iteration = 0;
        fxlower = funcOne(xlower);
        fxupper = funcOne(xupper);

        if (fxlower * fxupper >= 0) {
            AlertBox.display("Bisection Error", "This function can't be solved with bisection");
            return funcOneList;
        } else {
            do {

                // detemine xnew and f (xnew)
                xnew = (xlower + xupper) / 2.0;
                fxnew = funcOne(xnew);

                funcOneList[iteration] = xnew;

                iteration += 1;

                diff = Math.abs(xupper - xlower) / 2;

                bisectionTableLines.add(new TableLine(iteration, xlower, xupper, fxlower, fxupper, xnew, 0.0, diff));

                if (fxlower * fxnew > 0) {
                    xlower = xnew;
                    fxlower = fxnew;
                } else if (fxupper * fxnew > 0) {
                    xupper = xnew;
                    fxupper = fxnew;
                }

            } while (diff > decimal && iteration < 1000);
            return funcOneList;

        }
    }

    public static double[] bisectFunctionTwo(double startingPoint, double secondStartingPoint, double decimal) {
        bisectionTableLines.clear();
        double[] funcTwoList = new double[1000];
        double xnew, fxlower, fxupper, fxnew, diff;
        double xlower = startingPoint;
        double xupper = secondStartingPoint;
        int iteration = 0;
        fxlower = funcTwo(xlower);
        fxupper = funcTwo(xupper);

        if (fxlower * fxupper >= 0) {
            AlertBox.display("Bisection Error", "This function can't be solved with bisection");
            return funcTwoList;
        } else {
            do {

                // detemine xnew and f (xnew)
                xnew = (xlower + xupper) / 2.0;
                fxnew = funcTwo(xnew);

                System.out.println("Approx for iteration " + iteration + " is " + xnew);
                funcTwoList[iteration] = xnew;

                iteration += 1;

                diff = Math.abs(xupper - xlower) / 2;

                bisectionTableLines.add(new TableLine(iteration, xlower, xupper, fxlower, fxupper, xnew, 0.0, diff));

                if (fxlower * fxnew > 0) {
                    xlower = xnew;
                    fxlower = fxnew;
                } else if (fxupper * fxnew > 0) {
                    xupper = xnew;
                    fxupper = fxnew;
                }

            } while (diff > decimal && iteration < 1000);
            return funcTwoList;

        }
    }

    public static double[] bisectFunctionThree(double startingPoint, double secondStartingPoint, double decimal) {
        bisectionTableLines.clear();
        double[] funcThreeList = new double[1000];
        double xnew, fxlower, fxupper, fxnew, diff;
        double xlower = startingPoint;
        double xupper = secondStartingPoint;
        int iteration = 0;
        fxlower = funcThree(xlower);
        fxupper = funcThree(xupper);

        if (fxlower * fxupper >= 0) {
            AlertBox.display("Bisection Error", "This function can't be solved with bisection");
            return funcThreeList;
        } else {
            do {

                // detemine xnew and f (xnew)
                xnew = (xlower + xupper) / 2.0;
                fxnew = funcThree(xnew);

                funcThreeList[iteration] = xnew;

                iteration += 1;

                diff = Math.abs(xupper - xlower) / 2;

                bisectionTableLines.add(new TableLine(iteration, xlower, xupper, fxlower, fxupper, xnew, 0.0, diff));

                if (fxlower * fxnew > 0) {
                    xlower = xnew;
                    fxlower = fxnew;
                } else if (fxupper * fxnew > 0) {
                    xupper = xnew;
                    fxupper = fxnew;
                }

            } while (diff > decimal && iteration < 1000);
            return funcThreeList;

        }
    }

    public static ObservableList<TableLine> getBisectionLines() {
        return bisectionTableLines;
    }
}
