package bakribrahim.com.moveappdemo.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiclient {
    static String url ="http://api.themoviedb.org/3/";
    static Retrofit retrofit = null;


    public static Retrofit getInstance (){
        if (retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
