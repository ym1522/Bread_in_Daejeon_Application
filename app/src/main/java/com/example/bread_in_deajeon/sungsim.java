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
//필요한 클래스 import

public class sungsim extends AppCompatActivity {
    /*빵의 이름, 이미지, 가격, 평점을 저장할 배열들을 생성하여 필요한 리소스들을 저장해준다.*/
    ListView list;
    String[] breadName = {"보문산메아리", "튀김소보로", "명란바게트",
            "작은메아리", "토요빵", "홈런왕", "카카오순정", "후렌치파이", "오보름달"};
    Integer[] breadImage = {
            R.drawable.s1, R.drawable.s2, R.drawable.s3,
            R.drawable.s4, R.drawable.s5, R.drawable.s6,
            R.drawable.s7, R.drawable.s8, R.drawable.s9,};
    String[] breadPrice = {"5,000원", "1,500원","3,500원",
            "2,500원", "3,500원", "4,500원",
            "2,500원", "1,500원", "2,500원"};
    String[] breadRating = {"★4.5", "★4.6", "★3.9", "★4.4", "★3.7", "★3.8", "★4.1","★3.6", "★4.0"};


    // onCreate() 메소드를 재정의하여 초기화할 부분을 적어준다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bread_list);    //bread_list.xml을 액티비티의 화면으로 설정한다.

        //리스트 뷰의 항목을 표시하기 위한 어댑터 생성
        /*어댑터는 여러 개의 데이터를 화면에 표시할 때 묶어서 정리해주는 역할을 한다.*/
        CustomList1 adapter = new CustomList1(sungsim.this);
        list = (ListView) findViewById(R.id.listView);  //xml에서 생성한 리스트뷰와 코드를 연결해준다.
        list.setAdapter(adapter);   //리스트 뷰에 어댑터를 설정한다.

        //xml에서 생성한 버튼들과 코드를 연결해준다.
        Button map = (Button) findViewById(R.id.map);
        Button call = (Button) findViewById(R.id.call);
        Button site = (Button) findViewById(R.id.site);

        //map 버튼이 클릭되면 암시적 인텐트를 사용하여 기기 내 기본 어플인 구글 지도 맵으로 이동한다.
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:36.32777861869717, 127.42726464680895"));    //해당 빵집의 위도와 경도 정보 입력
                startActivity(intent);
            }
        });

        //call 버튼이 클릭되면 암시적 인텐트를 사용하여 기기 내 기본 어플인 전화(dial) 화면으로 이동한다.
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:15888069"));  //해당 빵집의 전화번호 입력
                startActivity(intent);
            }
        });

        //site 버튼이 클릭되면 암시적 인텐트를 사용하여 기기 내 기본 브라우저로 이동, 웹 사이트를 화면에 표시한다.
        site.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.sungsimdangmall.co.kr/"));   //해당 빵집의 웹사이트 주소 입력
                startActivity(intent);
            }
        });
    }

    //ArrayAdapter를 상속받는 커스텀 리스트뷰를 생성한다.
    public class CustomList1 extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList1(Activity context){   //CustomList1 생성자 구현
            super(context, R.layout.bread_item, breadName); //아이템에 사용할 레이아웃 정의
            this.context = context;
        }

        //getView()는 어댑터에 추가된 리소스들을 표시하는 View 객체를 반환
        //getView() 메소드가 반환하는 뷰를 사용하여 항목을 표시한다.
        @Override   //getView()를 오버라이드해 필요한 동작을 수행하도록 한다.
        public View getView(int position, View view, ViewGroup parent) {
            //bakery_list_item.xml을 Inflater로써 view 객체로 생성한다.
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.bread_item, null, true);

            //이미지, 이름, 가격, 평점을 가진 데이터들을 코드와 연결
            ImageView bimageView = (ImageView) rowView.findViewById(R.id.breadImage);
            TextView btitle = (TextView) rowView.findViewById(R.id.breadName);
            TextView brating = (TextView) rowView.findViewById(R.id.breadRating);
            TextView bprice = (TextView) rowView.findViewById(R.id.breadPrice);

            //각각의 배열을 활용하여 항목들을 설정한다.
            btitle.setText(breadName[position]);
            bimageView.setImageResource(breadImage[position]);
            brating.setText(breadRating[position]);
            bprice.setText(breadPrice[position]);

            return rowView;
        }
    }
}

