package ch.racic.selendroid.democalc;

/**
 * Created by rac on 04.05.14.
 */
public enum CalcOperation {
    divide("÷"), multiply("*"), minus("-"), plus("+"), equal("="), percent("%");

    private String opdisp;
    CalcOperation(String value) {
        opdisp = value;
    }


    @Override
    public String toString() {
        return opdisp;
    }
}
