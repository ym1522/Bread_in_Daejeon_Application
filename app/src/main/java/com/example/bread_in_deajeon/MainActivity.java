package com.example.bread_in_deajeon;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// 필요한 class를 import 해준다.

public class MainActivity extends AppCompatActivity {

    // 재정의된 onCreate() 메소드에서 초기화 할 부분을 써준다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  //main.xml을 액티비티의 화면으로 설정한다.

        // 버튼 객체를 만들고 xml의 버튼 두 개를 식별자를 이용해 java 파일과 연결해준다.
        Button go_bakery = (Button) findViewById(R.id.goBakery);
        Button go_request = (Button) findViewById(R.id.goRequest);

        //goBakery 버튼을 누르면 무명 클래스를 이용해 이벤트 처리가 됨
        go_bakery.setOnClickListener(new View.OnClickListener() {

            //재정의된 onClick() 메소드에서 화면 전환하는 코드를 써준다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, bakery_list.class);
                startActivity(intent);  // 인텐트를 생성해 bakery_list 액티비티로 이동한다.
            }
        });

        //goRequest 버튼을 누르면 무명 클래스를 이용해 이벤트 처리가 됨
        go_request.setOnClickListener(new View.OnClickListener() {

            //재정의된 onClick() 메소드에서 화면을 전환하는 코드를 써준다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, request.class);
                startActivity(intent);  //인텐트를 생성해 request 액티비티로 이동한다.
            }
        });
    }
}
