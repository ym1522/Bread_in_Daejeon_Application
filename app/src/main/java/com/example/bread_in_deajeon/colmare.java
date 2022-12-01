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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class colmare extends AppCompatActivity {
    ListView list;
    String[] breadName = {"뉴욕치즈타르트", "호두트위스트", "먹물치즈바게트", "치즈퐁당", "딸기조아", "까마쿤", "핫도그모찌"};
    Integer[] breadImage = {
            R.drawable.c1, R.drawable.c2, R.drawable.c3,
            R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7,};
    String[] breadPrice = {"3,300원", "4,000원", "4,000원", "4,200원", "4,500원", "3,900원", "2,500원"};
    String[] breadRating = {"★3.7", "★3.7", "★4.2", "★4.0", "★4.8", "★3.9", "★4.5"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_list);

        CustomList1 adapter = new CustomList1(colmare.this);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        Button map = (Button) findViewById(R.id.map);
        Button call = (Button) findViewById(R.id.call);
        Button site = (Button) findViewById(R.id.site);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.363617813469766, 127.35711023068124"));
                startActivity(intent);
            }
        });



        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0428630206"));
                startActivity(intent);
            }
        });

        site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/colmar_bread/"));
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
