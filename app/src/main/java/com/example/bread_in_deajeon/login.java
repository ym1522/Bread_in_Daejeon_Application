package com.example.bread_in_deajeon;
import android.content.Intent;
import android.os.Bundle;
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
//필요한 클래스들을 import 하는 문장들

public class login extends AppCompatActivity {
    // 필요한 변수들을 선언해준다.
    private Button login;
    private Button signup;
    private EditText email;
    private EditText pw;
    FirebaseAuth auth;

    //애플리케이션의 첫 실행화면이다. onCreate()에는 초기화 할 부분을 적어준다.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // login.xml을 화면으로 설정한다.

        // xml의 버튼들을 id를 이용해 위에서 선언한 변수들과 연결시킨다.
        login = (Button) findViewById(R.id.Btn_login);
        signup = (Button) findViewById(R.id.Btn_signup);
        email = (EditText) findViewById(R.id.idput);
        pw = (EditText) findViewById(R.id.pwput);

        // FirebaseAuth 객체인 auth의 인스턴스를 받아온다.
        auth = FirebaseAuth.getInstance();

    }

    //Btn_login 버튼을 클릭하면 호출되는 이벤트 처리 메소드
    public void logBtnClick(View view){

        // 에디트텍스트 email과 passwaord에 적은 이메일, 비밀번호를 받아서 String형으로 변환한다.
        String Email = email.getText().toString().trim();
        String PW = pw.getText().toString().trim();

        /*FirebaseAuth 객체의 메소드 signInWithEmailAndPassword를 호출하여 로그인을 시도한다.
        * 매개변수는 위의 코드에서 String형으로 변환한 Email과 PW이다.*/
        auth.signInWithEmailAndPassword(Email, PW).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    // 오버라이드된 onComplete() 메소드를 호출
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){    //로그인에 성공하면
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent); //인텐트를 생성해 MainAcitivity를 실행한다.
                            //로그인에 성공했다는 토스트 메시지를 띄운다.
                            Toast.makeText(login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        }
                        else { // 로그인에 실패하면 로그인 오류라는 토스트 메시지를 띄운다.
                            Toast.makeText(login.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }

    // Btn_signup 버튼을 클릭하면 호출되는 이벤트 처리 메소드
    public void signBtnClick(View view){
        Intent intent = new Intent(login.this, signUp.class);
        startActivity(intent);  // 인텐트를 생성해 signUp 액티비티를 실행한다.
    }
}
