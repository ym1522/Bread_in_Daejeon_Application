package com.example.bread_in_deajeon;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//필요한 클래스 import

public class request extends AppCompatActivity {
    /*FirebaseDatabase 객체와 DatabaseReference 객체를 생성하여 인스턴스를 받아온다.
    * 데이터베이스에서 데이터를 읽거나 쓰려면 DatabaseReference의 인스턴스가 필요하다.*/
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbref = db.getReference();

    //onCreate() 메소드를 재정의하여 초기화할 내용을 적는다.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);   //request.xml을 액티비티의 화면으로 설정한다.

        //xml에서 생성한 버튼과 에디트텍스트를 코드와 연결해준다.
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        EditText editText = (EditText) findViewById(R.id.edit);

        //button1(제출하기) 버튼을 클릭하면 무명클래스를 사용해 이벤트처리를 한다.
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ //버튼을 클릭하면 onClick() 메소드 call
                //에디트텍스트에 작성한 내용을 String 형태로 바꿔 addReq()메소드로 전달한다.
                addReq(editText.getText().toString());

                //버튼을 누르면 요청이 완료되었다는 토스트 메시지를 띄운다.
                Toast.makeText(request.this, "요청이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //button2(메인으로 돌아가기) 버튼을 클릭하면 무명클래스를 사용해 이벤트를 처리한다.
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ //버튼을 클릭하면 onClick() 메소드 call
                //인텐트를 생성하여 MainActivity로 이동한다.
                Intent intent = new Intent(request.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addReq(String name) {   //에디트텍스트에 작성된 내용(name)이 입력으로 들어온다.
        req req = new req(name);    //name을 인자로 갖는 req 객체를 생성한다(req 클래스는 아래에서 정의).
        dbref.child("name").push().setValue(name);  /*setValue() 메소드를 사용하여 쓰기 작업을 할 수 있다.
        지정된 참조에 데이터를 저장한다. 해당 코드는 "name"이라는 항목에 데이터를 추가한다.*/
    }

    public class req {  //값을 추가할 때 사용하는 메소드
        String name;    //String 형 변수 생성
        public req(String name){    //String 형 데이터(name)를 저장한다.
            this.name = name;
        }
    }
}

