package milton.myimc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String arquivo, preference;
    Uri outputFileUri;
    Button buttonNewPhoto, buttonCalcular;
    EditText editTextPeso, editTextAltura;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor shared;
    double peso, altura, imc;
    String resultado;
    private ArrayList<ItemSpinner> itens;
    private ItemSpinner itemSpinner;
    private Spinner spinner;
    private AdapterSpinner adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNewPhoto = (Button) findViewById(R.id.buttonNewPhoto);
        spinner = (Spinner) findViewById(R.id.spinner);
        buttonCalcular = (Button) findViewById(R.id.buttonCalcular);
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);

        loadShared();
        loadSpinner();


    }

    //Chamado quando acionado pelo usuário
    public void abrirCamera(View v) {
        //Cria uma intenção para abrir a camera fotográfica
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Informa que a camera a ser aberta é a frontal
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);

        //Montagem do caminho onde o arquivo será salvo
        arquivo = Environment.getExternalStorageDirectory() + "/Pictures/fotoMyIMC.jpg";

        //Abre o caminho onde a foto será salva
        File file = new File(arquivo);
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        //Abre a camera
        startActivityForResult(intent, RESULT_FIRST_USER);
    }


    //Ao clicar na imagem que foi carregada
    //Não esquecer de incluir o atributo andoird:clickable="true" no <ImageView />
    public void verImagem(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        arquivo = Environment.getExternalStorageDirectory() + "/Pictures/fotoMyIMC.jpg";
        intent.setDataAndType(Uri.parse("file://" + arquivo), "image/*");
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        //TODO Auto-generated method stub
        super.onResume();
        carregaImagem();
    }

    //Ação para mostrar a foto no imageView
    public void carregaImagem() {
        ImageView imageView = (ImageView) findViewById(R.id.foto);
        arquivo = Environment.getExternalStorageDirectory() + "/Pictures/fotoMyIMC.jpg";
        imageView.setImageURI(Uri.parse(arquivo));
    }

    public void loadSpinner() {

        itens = new ArrayList<ItemSpinner>();

        ItemSpinner item1 = new ItemSpinner("Masculino");
        ItemSpinner item2 = new ItemSpinner("Feminino");

        itens.add(item1);
        itens.add(item2);

        //Cria o adapter
        adapterSpinner = new AdapterSpinner(this, itens);

        //Define o adapter
        spinner.setAdapter(adapterSpinner);


    }


    public void calculaImc(View view) {

        peso = Double.parseDouble(editTextPeso.getText().toString());
        altura = Double.parseDouble(editTextAltura.getText().toString());
        imc = 0;
        resultado = "";


        if (spinner.getSelectedItem().toString().equals("Masculino")) {

            imc = peso / (altura * altura);

            if (imc < 20.7) {
                resultado = "Abaixo do peso ideal";
            } else if (imc <= 26.4) {
                resultado = "No peso ideal";
            } else if (imc <= 27.8) {
                resultado = "Um pouco acima do peso ideal";
            } else if (imc <= 31.1) {
                resultado = "acima do peso ideal";
            } else if (imc > 31.1) {
                resultado = "Obeso";
            }
        } else {
            imc = peso / (altura * altura);

            if (imc < 19.1) {
                resultado = "Abaixo do peso ideal";
            } else if (imc <= 25.8) {
                resultado = "No peso ideal";
            } else if (imc <= 27.3) {
                resultado = "Um pouco acima do peso ideal";
            } else if (imc <= 32.3) {
                resultado = "acima do peso ideal";
            } else if (imc > 32.3) {
                resultado = "Obeso";
            }
        }

        //passar o resultado para a resultActivity


        shared = sharedpreferences.edit();
        shared.putString("peso", String.valueOf(peso));
        shared.putString("altura", String.valueOf(altura));

        shared.commit();

        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);

        DecimalFormat df = new DecimalFormat("###,##0.00");
        intent.putExtra("imc", String.valueOf(df.format(imc)));
        intent.putExtra("resultado", resultado);
        startActivity(intent);


    }

    public void loadShared() {

        sharedpreferences = getSharedPreferences(preference,
                Context.MODE_PRIVATE);

        editTextAltura.setText(sharedpreferences.getString("altura", String.valueOf(altura)));
        editTextPeso.setText(sharedpreferences.getString("peso", String.valueOf(peso)));
    }

}
