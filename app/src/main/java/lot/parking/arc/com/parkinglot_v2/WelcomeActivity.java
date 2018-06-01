package lot.parking.arc.com.parkinglot_v2;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //发送短信验证码
                sendCode(getApplication());
            }
        },3000);



    }

    public void sendCode(Context context) {
        RegisterPage page = new RegisterPage();
        //如果使用我们的ui，没有申请模板编号的情况下需传null
        page.setTempCode(null);
        page.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 处理成功的结果
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                    String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                    // TODO 利用国家代码和手机号码进行后续的操作

                    //启动界面
                   //Intent intent = new Intent(WelcomeActivity.this,ChronometerActivity.class);
                   Intent intent = new Intent(WelcomeActivity.this,TCPServerActivity.class);
                    startActivity(intent);

                } else{
                    // TODO 处理错误的结果
                    Toast.makeText(WelcomeActivity.this, "验证码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        page.show(context);
    }



}
