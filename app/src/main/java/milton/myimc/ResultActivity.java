package milton.myimc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultado, tvImc;
    Button buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultado = (TextView) findViewById(R.id.textViewResultado);
        tvImc = (TextView) findViewById(R.id.textViewImc);
        buttonReturn = (Button) findViewById(R.id.buttonReturn);

        showResult();

    }


    public void showResult() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String resultado = bundle.getString("resultado");
        String imc = bundle.getString("imc");

        tvResultado.setText(resultado);
        tvImc.setText(imc);

    }

    public void returnActivity(View view) {

        this.finish();

    }


}
