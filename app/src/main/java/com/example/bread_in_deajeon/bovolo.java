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

public class bovolo extends AppCompatActivity {
    ListView list;
    String[] breadName = {"초코크로아상", "오징어먹물빵","순수우유롤",
            "치즈마블링", "고구마빵", "감자빵",
            "폭신폭신몽블랑", "오징어슈레드", "토요빵", "호두찰빵"};
    Integer[] breadImage = {
            R.drawable.b1, R.drawable.b2, R.drawable.b3,
            R.drawable.b4, R.drawable.b5, R.drawable.b6,
            R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10};
    String[] breadPrice = {"3,300원", "4,300원", "12,000원",
            "5,600원", "2,500원", "2,500원",
            "4,500원", "3,700원", "4,000원", "2,800원"};
    String[] breadRating = {"★4.0", "★3.7", "★4.2", "★3.9", "★4.3",
            "★4.1", "★3.5", "★3.9", "★4.0", "★3.8"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_list);

        CustomList1 adapter = new CustomList1(bovolo.this);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        Button map = (Button) findViewById(R.id.map);
        Button call = (Button) findViewById(R.id.call);
        Button site = (Button) findViewById(R.id.site);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.36739877428906, 127.43752327672757"));
                startActivity(intent);
            }
        });



        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0426320445"));
                startActivity(intent);
            }
        });

        site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/bovolobakery/"));
                startActivity(intent);
            }
        });
    }

    public class CustomList1 extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList1(Activity context){
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
