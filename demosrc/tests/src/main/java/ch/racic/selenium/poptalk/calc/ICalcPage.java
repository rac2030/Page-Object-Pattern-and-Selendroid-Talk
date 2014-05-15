/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 * This code has been developed as part of talk on selendroid by Michel Racic (http://rac.su/+) from GDG Zürich (http://www.gdgzh.ch).
 */

package ch.racic.selenium.poptalk.calc;

public interface ICalcPage {
    public boolean verifyInitialized();
    public double displayed();
    public ICalcPage clear();
    public ICalcPage clearEntry();
    public ICalcPage backspace();
    public ICalcPage percent();
    public ICalcPage divide();
    public ICalcPage multiply();
    public ICalcPage add();
    public ICalcPage substract();
    public double equal();
    public ICalcPage dot();
    public ICalcPage n0();
    public ICalcPage n1();
    public ICalcPage n2();
    public ICalcPage n3();
    public ICalcPage n4();
    public ICalcPage n5();
    public ICalcPage n6();
    public ICalcPage n7();
    public ICalcPage n8();
    public ICalcPage n9();
}
