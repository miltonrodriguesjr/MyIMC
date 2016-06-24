package milton.myimc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultado, tvImc, tvValorAvaliacao;
    Button buttonReturn;
    RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultado = (TextView) findViewById(R.id.textViewResultado);
        tvImc = (TextView) findViewById(R.id.textViewImc);
        buttonReturn = (Button) findViewById(R.id.buttonReturn);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvValorAvaliacao = (TextView) findViewById(R.id.tvValorAvaliacao);

        addListenerOnRatingBar();
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

    public void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvValorAvaliacao = (TextView) findViewById(R.id.tvValorAvaliacao);

        //se o valor de avaliação mudar,
        //exiba o valor de avaliação atual no resultado (textview) automaticamente
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
                tvValorAvaliacao.setText(String.valueOf(avaliacao));
            }
        });
    }

//    public void addListenerOnButton() {
//        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
//        btnSubmit = (Button) findViewById(R.id.btnSubmit);
//
//        //se o botão for clicado, exiba o valor de avaliação corrente.
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ResultActivity.this,
//                        String.valueOf(ratingBar.getRating()),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
