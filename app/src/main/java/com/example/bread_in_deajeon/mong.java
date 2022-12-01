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

public class mong extends AppCompatActivity {
    ListView list;
    String[] breadName = {"밀키연유마들렌", "민트초코칩브라우니", "딸기빅토리아케이크",
            "고구마꿀파운드", "오레오브라우니", "프레첼머랭쿠키", "애플얼그레이쿠키"};
    Integer[] breadImage = {
            R.drawable.m1, R.drawable.m2, R.drawable.m3,
            R.drawable.m4, R.drawable.m5, R.drawable.m6, R.drawable.m7};
    String[] breadPrice = {"2,300원", "4,700원", "6,000원",
            "4,300원", "4,900원", "4,400원", "3,200원"};
    String[] breadRating = {"★3.6", "★3.9", "★4.8", "★3.7", "★4.5", "★3.4", "★4.0"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_list);

        CustomList1 adapter = new CustomList1(mong.this);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        Button map = (Button) findViewById(R.id.map);
        Button call = (Button) findViewById(R.id.call);
        Button site = (Button) findViewById(R.id.site);

        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.35541442609979, 127.42277261534552"));
                startActivity(intent);
            }
        });



        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01044591014"));
                startActivity(intent);
            }
        });

        site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/_creative_mongsim/?hl=ko"));
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
