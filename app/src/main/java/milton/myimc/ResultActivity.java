package milton.myimc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultado, tvImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultado = (TextView) findViewById(R.id.textViewResultado);
        tvImc = (TextView) findViewById(R.id.textViewImc);

    }


    public void showResult() {

        Bundle bundle = new Bundle();
        String imc = String.valueOf(bundle.getBundle("imc"));
        String resultado = String.valueOf(bundle.getBundle("resultado"));

        tvResultado.setText(resultado);
        tvImc.setText(imc);

    }


}
