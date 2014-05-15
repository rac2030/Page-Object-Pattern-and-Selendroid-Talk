package ch.racic.selendroid.democalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView display;
    private final CalcImpl calc;

    public MainActivity() {
        calc = new CalcImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.result);
        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0:
                        calc.enterNumber("0");
                        break;
                    case R.id.button1:
                        calc.enterNumber("1");
                        break;
                    case R.id.button2:
                        calc.enterNumber("2");
                        break;
                    case R.id.button3:
                        calc.enterNumber("3");
                        break;
                    case R.id.button4:
                        calc.enterNumber("4");
                        break;
                    case R.id.button5:
                        calc.enterNumber("5");
                        break;
                    case R.id.button6:
                        calc.enterNumber("6");
                        break;
                    case R.id.button7:
                        calc.enterNumber("7");
                        break;
                    case R.id.button8:
                        calc.enterNumber("8");
                        break;
                    case R.id.button9:
                        calc.enterNumber("9");
                        break;
                    case R.id.buttonac:
                        calc.ac();
                        break;
                    case R.id.buttonce:
                        calc.ce();
                        break;
                    case R.id.buttonbackspace:
                        calc.bs();
                        break;
                    case R.id.buttondot:
                        calc.enterNumber(".");
                        break;
                    case R.id.buttonpercent:
                        calc.useOperation(CalcOperation.percent);
                        break;
                    case R.id.buttonplus:
                        calc.useOperation(CalcOperation.plus);
                        break;
                    case R.id.buttonminus:
                        calc.useOperation(CalcOperation.minus);
                        break;
                    case R.id.buttonmultiply:
                        calc.useOperation(CalcOperation.multiply);
                        break;
                    case R.id.buttondivide:
                        calc.useOperation(CalcOperation.divide);
                        break;
                    case R.id.buttonequal:
                        calc.useOperation(CalcOperation.equal);
                    default:
                }
                updateDisplay();
            }
        };

        findViewById(R.id.button0).setOnClickListener(btnClick);
        findViewById(R.id.button1).setOnClickListener(btnClick);
        findViewById(R.id.button2).setOnClickListener(btnClick);
        findViewById(R.id.button3).setOnClickListener(btnClick);
        findViewById(R.id.button4).setOnClickListener(btnClick);
        findViewById(R.id.button5).setOnClickListener(btnClick);
        findViewById(R.id.button6).setOnClickListener(btnClick);
        findViewById(R.id.button7).setOnClickListener(btnClick);
        findViewById(R.id.button8).setOnClickListener(btnClick);
        findViewById(R.id.button9).setOnClickListener(btnClick);
        findViewById(R.id.buttonac).setOnClickListener(btnClick);
        findViewById(R.id.buttonce).setOnClickListener(btnClick);
        findViewById(R.id.buttonbackspace).setOnClickListener(btnClick);
        findViewById(R.id.buttondot).setOnClickListener(btnClick);
        findViewById(R.id.buttonpercent).setOnClickListener(btnClick);
        findViewById(R.id.buttonplus).setOnClickListener(btnClick);
        findViewById(R.id.buttonminus).setOnClickListener(btnClick);
        findViewById(R.id.buttonmultiply).setOnClickListener(btnClick);
        findViewById(R.id.buttondivide).setOnClickListener(btnClick);
        findViewById(R.id.buttonequal).setOnClickListener(btnClick);

    }

    private void updateDisplay() {
        display.setText(calc.getDisplay());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
