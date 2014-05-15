package ch.racic.selendroid.democalc;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by rac on 04.05.14.
 */
public class CalcImpl {
    private Double result;
    private Double first;
    private String firstPartial;
    private Double second;
    private String secondPartial;
    private CalcOperation operation;

    public CalcImpl() {
        result = new Double(0);
    }

    public String enterNumber(String number) {
        if(first != null) {
            if(secondPartial != null) {
                secondPartial += number;
            } else {
                secondPartial = number;
            }
        } else {
            if (firstPartial != null) {
                firstPartial += number;
            } else {
                firstPartial = number;
            }
        }
        return getDisplay();
    }

    public String useOperation(CalcOperation op) {
        if(operation != null) {
            if (first != null && second == null) {
                second = new Double(secondPartial);
                secondPartial = null;
                calculateResult();
            }
        } else {
            if(first == null && second == null) {
                first = new Double(firstPartial);
                firstPartial = null;
            }
        }
        if(op != CalcOperation.equal)
            operation = op;
        return getDisplay();
    }

    public String ac() {
        first = null;
        firstPartial = null;
        second = null;
        secondPartial = null;
        operation = null;
        result = new Double(0);
        return getDisplay();
    }

    public String ce() {
        if(firstPartial != null) {
            firstPartial = null;
        } else if (secondPartial != null) {
            secondPartial = null;
        } else if (operation != null) {
            operation = null;
            firstPartial = first.toString();
            first = null;
        }
        return getDisplay();
    }

    public String bs() {
        if(firstPartial != null) {
            firstPartial = firstPartial.substring(0, firstPartial.length()-1);
        } else if (secondPartial != null) {
            secondPartial = secondPartial.substring(0, secondPartial.length()-1);
        }
        return getDisplay();
    }

    private String calculateResult() {
        switch(operation) {
            case divide:
                result = first / second;
                break;
            case multiply:
                result = first * second;
                break;
            case minus:
                result = first - second;
                break;
            case plus:
                result = first + second;
                break;
            case percent:
            default:
                throw new UnsupportedOperationException(operation + " Operation not implemented");

        }
        second = null;
        operation = null;
        first = result;
        return getDisplay();
    }

    public String getDisplay() {
        String ret = "";
        if(first == null) {
            if(firstPartial == null){
                ret += doubleString(result);
            } else if(operation != null) {
                ret += 0;
            } else {
                ret = firstPartial;
            }
        } else if (second == null) {
            if(operation == null) {
                ret += doubleString(first);
            } else if(secondPartial == null){
                ret += 0;
            } else {
                ret = secondPartial;
            }
        }
        return ret;
    }

    private String doubleString(Double d) {
        String ret = new BigDecimal(Double.toString(d)).stripTrailingZeros().toPlainString();
        if(ret.equals("0.0")) {
            // Very old bug in BigDecimal...
            ret = "0";
        }
        return ret;
    }


}
