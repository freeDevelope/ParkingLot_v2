package lot.parking.arc.com.parkinglot_v2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    private TextView mTextViewMoney;
    private Button mBtnPayMoney;

    private float mParkingMoney = 20.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initView();

        mBtnPayMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setTitle("本次停车费:"+"￥"+mParkingMoney);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PayActivity.this, "支付成功,欢迎下次停车！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(PayActivity.this, "未支付成功！", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.create().show();

            }


        });




    }

    private void initView(){
        mTextViewMoney = findViewById(R.id.text_view_money);
        mBtnPayMoney = findViewById(R.id.btn_pay_money);
    }
}
