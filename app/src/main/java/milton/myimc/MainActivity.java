package milton.myimc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    String arquivo;
    Uri outputFileUri;
    Button buttonNewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNewPhoto = (Button) findViewById(R.id.buttonNewPhoto);

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

}
