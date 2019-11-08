/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmllinegraph;

/**
 *
 * @author samuel
 */
public class TableLine {

    private double xold1;
    private double xold2;
    private double fxold1;
    private double fxold2;
    private double fdashold;
    private double xnew;
    private double diff;
    private double step;

    public TableLine() {
        this.xold1 = 0;
        this.xold2 = 0;
        this.fxold1 = 0;
        this.fxold2 = 0;
        this.fdashold = 0;
        this.xnew = 0;
        this.diff = 0;
        this.diff = step;
    }

    public TableLine(double step, double xold1, double xold2, double fxold1, double fxold2, double xnew, double fdashold, double diff) {
        this.step = step;
        this.xold1 = xold1;
        this.xold2 = xold2;
        this.fxold1 = fxold1;
        this.fxold2 = fxold2;
        this.fdashold = fdashold;
        this.xnew = xnew;
        this.diff = diff;
    }

    public double getXold1() {
        return xold1;
    }

    public void setXold1(double xold1) {
        this.xold1 = xold1;
    }

    public double getXold2() {
        return xold2;
    }

    public void setXold2(double xold2) {
        this.xold2 = xold2;
    }

    public double getFxold1() {
        return fxold1;
    }

    public void setFxold1(double fxold1) {
        this.fxold1 = fxold1;
    }

    public double getFxold2() {
        return fxold2;
    }

    public void setFxold2(double fxold2) {
        this.fxold2 = fxold2;
    }

    public double getFdashold() {
        return fdashold;
    }

    public void setFdashold(double fdashold) {
        this.fdashold = fdashold;
    }

    public double getXnew() {
        return xnew;
    }

    public void setXnew(double xnew) {
        this.xnew = xnew;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

}
