package com.example.bread_in_deajeon;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//필요한 클래스들을 import 해줌

public class bakery_list extends AppCompatActivity {
    /*빵집 이름, 로고 이미지, 평점, 화면을 전환할 액티비티들을 저장할 배열들을 생성하여
    * 필요한 리소스들을 저장해준다.*/
    ListView list;
    String[] titles = {"성심당", "하레하레", "보보로 베이커리", "몽심", "콜마르 브레드"};
    Integer[] images = {
            R.drawable.sungsim_logo, R.drawable.harehare_logo,
            R.drawable.bovolo_logo, R.drawable.mong_logo, R.drawable.colmare_logo};
    String[] ratings = {"★4.5", "★4.2", "★4.8", "★3.9", "★3.8"};
    Class[] intents = {sungsim.class, harehare.class, bovolo.class, mong.class, colmare.class};

    //onCreate() 메소드를 재정의하여 초기화할 부분을 적는다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bakery_list);   // bakery_list.xml을 액티비티의 화면으로 설정

        //리스트 뷰의 항목을 표시하기 위한 어댑터 생성
        /*어댑터는 여러 개의 데이터를 화면에 표시할 때 묶어서 정리해주는 역할을 한다.*/
        CustomList0 adapter = new CustomList0(bakery_list.this);
        list = (ListView) findViewById(R.id.bakerylist);    //xml에서 생성한 리스트뷰와 코드를 연결해준다.
        list.setAdapter(adapter);   //리스트 뷰에 어댑터를 설정한다.

        //리스트 항목이 클릭되면 호출되는 메소드 구현
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override   //사용자가 리스트 뷰 항목을 클릭하면 onItemClick()이 호출
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //인텐트를 생성하여 intents 배열에 저장된 액티비티(클래스)로 화면을 전환한다.
                Intent intent = new Intent(bakery_list.this, intents[+position]);
                startActivity(intent);
            }
        });
    }

    //ArrayAdapter를 상속받는 커스텀 리스트뷰를 생성한다.
    public class CustomList0 extends ArrayAdapter<String>{
        private final Activity context;
        public CustomList0(Activity context){   //CustomList0 생성자 구현
            super(context, R.layout.bakery_list_item, titles);  //아이템에 사용할 레이아웃을 정의
            this.context = context;
        }

        //getView()는 어댑터에 추가된 리소스들을 표시하는 View 객체를 반환
        //getView() 메소드가 반환하는 뷰를 사용하여 항목을 표시한다.
        @Override   //getView()를 오버라이드해 필요한 동작을 수행하도록 한다.
        public View getView(int position, View view, ViewGroup parent) {

            //bakery_list_item.xml을 Inflater로써 view 객체로 생성한다.
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.bakery_list_item, null, true);

            //이미지로고, 이름, 평점을 가진 데이터들을 코드와 연결
            ImageView imageView = (ImageView) rowView.findViewById(R.id.logo_image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);

            //각각의 배열을 활용하여 항목들을 설정한다.
            title.setText(titles[position]);
            imageView.setImageResource(images[position]);
            rating.setText(ratings[position]);

            return rowView;
        }
    }
}

