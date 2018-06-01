package lot.parking.arc.com.parkinglot_v2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerActivity extends AppCompatActivity {

    private TextView mTextViewIP;
    private TextView mTextViewPort;
    private TextView mTextViewCarPositionNum;
    private Button mBtnNext;

    private ServerSocket mServerSocket;
    private Socket mSocket;
    private final int mPort = 8888;
    private JSONObject mCarJSON;    //接收的JSON
    private int mCarPosistionNum;   //分配的车位
    private boolean isGetPositionNum = true;
    private static final String TEST_JSON_STR = "{\"parking_position\": \"10\"，\"parking_start_time\":{\"year\": \"2018\"，\"mouth\": \"5\"，\"day\": \"10\"，\"hour\": \"14\"，\"minute\": \"30\"，\"second\": \"00\"}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcpserver);

        //默认的JSON
       // mCarJSON = JsonHelper.getJsonObject(TEST_JSON_STR);

        initView();

        //显示本机的IP
        String ipAddr = IPUtils.getIpAddress(this);
        mTextViewIP.setText(ipAddr);

        //开启网络连接后台
        new TCPServerAsyncTask().execute();

        if (isGetPositionNum){
            mBtnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TCPServerActivity.this,ChronometerActivity.class);
                    //将json传递到下一个界面
                    //intent.putExtra("carPositionJSON",mCarJSON.toString());
                    startActivity(intent);
                }
            });
        }



    }

    private void initView(){
        mTextViewIP = findViewById(R.id.text_view_server_ip_addr);
        mTextViewPort = findViewById(R.id.text_view_server_port);
        mTextViewCarPositionNum = findViewById(R.id.text_view_car_position_num);
        mBtnNext = findViewById(R.id.btn_next);
    }

    private class TCPServerAsyncTask extends AsyncTask<String,Integer,Integer>{

        @Override
        protected Integer doInBackground(String... strings) {
            try {
                //实例化一个服务端Socket
                mServerSocket = new ServerSocket(mPort);
                //开始监听，等待客户端连接
                mSocket = mServerSocket.accept();

                //如果客户端已经连接/配对
//                if (mServerSocket.isBound()){
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(TCPServerActivity.this, "已连接TCP!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//
//
//                }

            }catch (IOException e){

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(TCPServerActivity.this, "等待分配车位...", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {

            if (mSocket.isConnected()){
                isGetPositionNum = true;
                Toast.makeText(TCPServerActivity.this, "TCP连接成功！", Toast.LENGTH_SHORT).show();
                //获取输入流，读取客户端发送的Json
//                InputStream inputStream = null;
//                try {
//                    inputStream = mSocket.getInputStream();
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    String info = null;
//                    try {
//                        while ((info = bufferedReader.readLine())!=null){
//                            Toast.makeText(TCPServerActivity.this, "收到的车位信息:"+info, Toast.LENGTH_SHORT).show();
//                        }
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//
//                    if (info!=null){
//                        if (!info.isEmpty()){
//                            //转换为JSON对象
//                            // mCarJSON = JsonHelper.getJsonObject(info);
//                        }
//                    }
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
            }


        }
    }


    private class TCPRecvThread extends Thread{

        private InputStream inputStream;
        private byte[] bytes;
        @Override
        public void run() {
            try {
               inputStream = mSocket.getInputStream();
            }catch (IOException e){
                e.printStackTrace();
            }
            while (true){

            }

        }
    }

}
