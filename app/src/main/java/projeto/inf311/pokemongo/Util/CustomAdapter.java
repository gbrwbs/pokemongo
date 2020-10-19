package projeto.inf311.pokemongo.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import projeto.inf311.pokemongo.R;

/**
 * Created by vanessa on 04/07/17.
 */

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private String[]  nomes;
    private int[] imge;
    private String [] posicoes;

    public CustomAdapter(Context context, String[] text1, int[] imageIds, String[] pos) {
        mContext = context;
        nomes = text1;
        imge = imageIds;
        posicoes = pos;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return nomes.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(MyApp.getAppContext());
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView nome;
        TextView pos;
        ImageView i1;
        i1 = (ImageView) row.findViewById(R.id.imgIcon);
        i1.setImageResource(imge[position]);

        nome = (TextView) row.findViewById(R.id.txtNome);
        nome.setText(nomes[position]);

        pos = (TextView) row.findViewById(R.id.txtPos);
        pos.setText(posicoes[position]);

        return (row);
    }
}
