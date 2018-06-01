package lot.parking.arc.com.parkinglot_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class ChronometerActivity extends AppCompatActivity {

    private Chronometer mChronometer;
    private Button mBtnEdnPark;

    private long mRecordTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);

        initView();
        //设置计时器的显示方式
        mChronometer.setFormat("停车时间\n%s");
        //开始计时
        startParking();
        Toast.makeText(this, "您好，现在开始停车计费", Toast.LENGTH_SHORT).show();

        //Button设置监听
        mBtnEdnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出一个对话框，确认结束停车
                AlertDialog.Builder builder = new AlertDialog.Builder(ChronometerActivity.this);
                builder.setTitle("您确定要结束停车吗？");

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //结束计时
                        Toast.makeText(ChronometerActivity.this, "已经结束停车", Toast.LENGTH_SHORT).show();
                        stopParking();

                        //
                        dialog.dismiss();

                        //跳转到支付界面
                        Intent intent = new Intent(ChronometerActivity.this,PayActivity.class);
                        startActivity(intent);

                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });


    }

    void initView(){
        mChronometer = findViewById(R.id.chronometer_park);
        mBtnEdnPark = findViewById(R.id.btn_end_parking);
    }


    private void startParking(){
        mChronometer.setBase(SystemClock.elapsedRealtime()-mRecordTime);
        mChronometer.start();
    }

    private void stopParking(){
        mChronometer.stop();
        mRecordTime = SystemClock.elapsedRealtime()-mChronometer.getBase();//保存这次记录的时间
    }


}
