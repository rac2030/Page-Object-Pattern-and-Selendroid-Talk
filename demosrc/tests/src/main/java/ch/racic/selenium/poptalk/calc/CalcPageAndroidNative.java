/*
 * Copyleft (c) 2014. This code is for learning purposes only. Do whatever you like with it but don't take it as perfect code.
 * This code has been developed as part of talk on selendroid by Michel Racic (http://rac.su/+) from GDG Zürich (http://www.gdgzh.ch).
 */

package ch.racic.selenium.poptalk.calc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalcPageAndroidNative implements ICalcPage{

    @FindBy(id = "result")
    private WebElement display;

    @FindBy(id="buttonac")
    private WebElement clear;

    @FindBy(id="buttonce")
    private WebElement clearEntry;

    @FindBy(id="buttonbackspace")
    private WebElement backspace;

    @FindBy(id="buttonpercent")
    private WebElement percent;

    @FindBy(id="buttondivide")
    private WebElement divide;

    @FindBy(id="buttonmultiply")
    private WebElement multiply;

    @FindBy(id="buttonminus")
    private WebElement substract;

    @FindBy(id="buttonplus")
    private WebElement add;

    @FindBy(id="buttonequal")
    private WebElement equal;

    @FindBy(id="buttondot")
    private WebElement dot;

    @FindBy(id="button0")
    private WebElement n0;

    @FindBy(id="button1")
    private WebElement n1;

    @FindBy(id="button2")
    private WebElement n2;

    @FindBy(id="button3")
    private WebElement n3;

    @FindBy(id="button4")
    private WebElement n4;

    @FindBy(id="button5")
    private WebElement n5;

    @FindBy(id="button6")
    private WebElement n6;

    @FindBy(id="button7")
    private WebElement n7;

    @FindBy(id="button8")
    private WebElement n8;

    @FindBy(id="button9")
    private WebElement n9;

    @FindBy(xpath = "//TextView[@id='action_bar_title']")
    private WebElement title;

    @Override
    public boolean verifyInitialized() {
        return title.getText().equals("DemoCalc");
    }

    public double displayed() {
        return Double.valueOf(display.getText());
    }

    public ICalcPage clear() {
        clear.click();
        return this;
    }

    public ICalcPage clearEntry() {
        clearEntry.click();
        return this;
    }

    public ICalcPage backspace() {
        backspace.click();
        return this;
    }

    public ICalcPage percent() {
        percent.click();
        return this;
    }

    public ICalcPage divide() {
        divide.click();
        return this;
    }

    public ICalcPage multiply() {
        multiply.click();
        return this;
    }

    public ICalcPage add() {
        add.click();
        return this;
    }

    public ICalcPage substract() {
        substract.click();
        return this;
    }

    public double equal() {
        equal.click();
        return Double.valueOf(display.getText());
    }

    public ICalcPage dot() {
        dot.click();
        return this;
    }

    public ICalcPage n0() {
        n0.click();
        return this;
    }

    public ICalcPage n1() {
        n1.click();
        return this;
    }

    public ICalcPage n2() {
        n2.click();
        return this;
    }

    public ICalcPage n3() {
        n3.click();
        return this;
    }

    public ICalcPage n4() {
        n4.click();
        return this;
    }

    public ICalcPage n5() {
        n5.click();
        return this;
    }

    public ICalcPage n6() {
        n6.click();
        return this;
    }

    public ICalcPage n7() {
        n7.click();
        return this;
    }

    public ICalcPage n8() {
        n8.click();
        return this;
    }

    public ICalcPage n9() {
        n9.click();
        return this;
    }
}
