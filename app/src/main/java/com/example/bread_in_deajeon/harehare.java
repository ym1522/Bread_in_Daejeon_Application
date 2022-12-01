package com.example.bread_in_deajeon;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class harehare extends AppCompatActivity {
    ListView list;
    String[] breadName = {"앙버터", "잠봉버거", "하레바게트",
            "딸기마롱", "딸기크로와상", "공주알밤식빵", "초코크로와상"};
    Integer[] breadImage = {
            R.drawable.h1, R.drawable.h2, R.drawable.h3,
            R.drawable.h4, R.drawable.h5, R.drawable.h6, R.drawable.h7};
    String[] breadPrice = {"3,500원", "4,200원", "4,900원",
            "3,900원", "4,000원", "5,300원", "3,000원"};
    String[] breadRating = {"★3.4", "★3.8", "★4.4", "★4.6", "★4.8", "★3.9", "★4.1"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_list);

        CustomList2 adapter = new CustomList2(harehare.this);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        Button map = (Button) findViewById(R.id.map);
        Button call = (Button) findViewById(R.id.call);
        Button site = (Button) findViewById(R.id.site);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 36.35166448581654, 127.39494082584305"));
                startActivity(intent);
            }
        });



        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0424831595"));
                startActivity(intent);
            }
        });

        site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hareharebread/"));
                startActivity(intent);
            }
        });
    }

    public class CustomList2 extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList2(Activity context){
            super(context, R.layout.bread_item, breadName);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.bread_item, null, true);
            ImageView bimageView = (ImageView) rowView.findViewById(R.id.breadImage);
            TextView btitle = (TextView) rowView.findViewById(R.id.breadName);
            TextView brating = (TextView) rowView.findViewById(R.id.breadRating);
            TextView bprice = (TextView) rowView.findViewById(R.id.breadPrice);
            btitle.setText(breadName[position]);
            bimageView.setImageResource(breadImage[position]);
            brating.setText(breadRating[position]);
            bprice.setText(breadPrice[position]);
            return rowView;
        }
    }
}
