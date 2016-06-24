package milton.myimc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aluno on 14/06/2016.
 */
public class AdapterSpinner extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<ItemSpinner> itens;

    public AdapterSpinner(Context context, ArrayList<ItemSpinner> itens) {

        //Itens que preenchem o listview
        this.itens = itens;
        //resposavel por pegar o layout do item
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        //Resgatar o item do ListView pelo position
        ItemSpinner item = itens.get(position);

        //Resgatar o layout a ser preenchido
        view = inflater.inflate(R.layout.item_spinner, null);

        //Resgatar o Textview e o imageView para inserção do conteudo
        TextView tvSexo = (TextView) view.findViewById(R.id.textSexo);

        //Mandando os dados para o listview
        tvSexo.setText(item.getSexo());
        return view;
    }
}
