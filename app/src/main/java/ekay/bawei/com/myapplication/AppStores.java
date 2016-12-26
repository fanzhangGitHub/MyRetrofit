package ekay.bawei.com.myapplication;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ZhangFanfan on 2016/12/25.
 */
public class AppStores {
//    请求地址 : http://ip.taobao.com/service/getIpInfo.php?ip=202.202.32.202

    //    1.定义一个接口
    public interface TaobaoIPService {
        //    1.1.get请求：通过@GET注解，指明访问的地址
        @GET("getIpInfo.php")//★这里最前面不能带“/”
//    1.2.定义一个请求网络，并且返回结果的方法（方法返回Call<IP> ），
//      通过@Query指定key，后面跟上value
        Call<IP> getIP(@Query("ip") String ip);
        /*********************************************************************************/
//    1.3.post请求：通过@FormUrlEncoded、和@POST注解，指明访问的地址
//        (★注意：千万别忘了@FormUrlEncoded)
        @FormUrlEncoded
        @POST("getIpInfo.php")
//    1.4.通过@Field来指定key，后面跟上value
        Call<IP> postIP(@Field("ip") String ip);
    }

    //    2.实例化retrofit，配置好请求地址和解析方式
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ip.taobao.com/service/")//★这里最后面必须能带“/”
            .addConverterFactory(GsonConverterFactory.create())//设置将json解析为javabean所用的方式
            .build();
    //    3.通过retrofit创建第一步定义的接口的实例，
//      供在外部直接通过该实例调用该接口的getIPad方法，完成网络请求
    static AppStores.TaobaoIPService taobaoIPService =
            retrofit.create(AppStores.TaobaoIPService.class);
}