package com.example.bread_in_deajeon;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;
import java.util.HashMap;
//필요한 클래스 import

public class signUp extends AppCompatActivity {

    //코드 작성에 필요한 변수들을 생성해준다.
    private static final String TAG = "signUp"; //Log의 태그
    private FirebaseAuth auth;
    private Button button;
    private EditText name;
    private EditText email;
    private EditText password;

    //onCreate() 메소드를 재정의해 초기화할 부분을 적어준다.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);    //signup.xml을 액티비티의 화면으로 설정한다.

        auth = FirebaseAuth.getInstance();  //FirebaseAuth 객체의 인스턴스를 가져온다.

        //xml에서 생성한 에디트텍스트, 버튼을 코드와 연결해준다.
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.go_signup);

        //signup 버튼을 클릭하면 무명클래스를 이용해 이벤트를 처리한다.
        button.setOnClickListener(new View.OnClickListener() {
            @Override   //버튼이 클릭되면 onClick() 메소드 call
            public void onClick(View view) {
                signUp();
            }   //signUp() 메소드를 call 한다(아래에 정의).
        });
    }

    private void signUp(){

        //사용자가 에디트텍스트에 적은 이메일과 비밀번호를 가져와 String 형태로 바꾸어 저장한다.
        String eemail = email.getText().toString().trim();
        String ppass = password.getText().toString().trim();

        //사용자의 이메일 주소와 비밀번호를 createUserWithEmailAndPassword()에 전달하여 신규 계정을 생성한다.
        auth.createUserWithEmailAndPassword(eemail, ppass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {  //계정 생성이 완료되면
                        /*Log는 앱 실행 중 특정 시점을 기록으로 남기는 것으로, 안드로이드 스튜디오의 Logcat 창에서 확인할 수 있다.
                        * v, i, d, e, w 총 다섯 개의 메소드가 존재한다. 인자로는 로그의 태그와 내용(메시지)을 지정한다.*/
                        Log.d(TAG, "성공");  //d 메서드는 debug를 의미한다.
                        FirebaseUser user = auth.getCurrentUser();  //FirebaseUser 객체를 생성하여 현재 사용자를 저장한다.

                        //인텐트를 생성하여 회원가입에 성공하면 login 액티비티로 이동한다.
                        Intent intent = new Intent(signUp.this, login.class);
                        startActivity(intent);
                        Toast.makeText(signUp.this, "회원가입성공\n로그인 화면으로 돌아갑니다.",
                                Toast.LENGTH_SHORT).show(); //회원가입에 성공했다는 토스트 메시지를 띄운다.
                        finish();
                    }
                    else{   //계정 생성에 실패하면
                        Log.w(TAG, "실패", task.getException());  //w 메서도는 warning을 의미한다.
                        Toast.makeText(signUp.this, "회원가입실패\n정확한 정보를 입력해주세요.",
                                Toast.LENGTH_SHORT).show(); //회원가입에 실패했다는 토스트 메시지를 띄운다.
                        if(ppass.length() < 6){ //입력된 비밀번호가 6자리 미만이면
                            Toast.makeText(signUp.this, "비밀번호는 6자리 이상입니다.",
                                    Toast.LENGTH_SHORT).show();
                            //6자리 이상으로 설정해달라는 토스트 메시지를 띄운다.
                        }
                        return;
                    }
            }
        });
    }
}
