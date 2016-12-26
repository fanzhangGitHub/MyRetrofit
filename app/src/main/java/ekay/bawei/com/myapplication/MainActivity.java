package ekay.bawei.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

//        1.调用getIP（或者postIP）方法得到Call
//        Call<IP> call = AppStores.taobaoIPService.getIP("202.202.32.202");
        Call<IP> call = AppStores.taobaoIPService.postIP("202.202.32.202");
//        2.call.enqueue开启异步网络请求
        call.enqueue(new Callback<IP>() {
            @Override
            public void onResponse(Call<IP> call, Response<IP> response) {
                IP body = response.body();
                String result = body.getData().toString();
//                3.可以直接更改UI，因为onResponse方法已经在UI线程中
                tv.setText(result);
//                4.取消请求
                call.cancel();
                Toast.makeText(MainActivity.this,call.isCanceled()+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<IP> call, Throwable t) {
                tv.setText(t.toString());
                t.printStackTrace();
            }
        });



    }
}