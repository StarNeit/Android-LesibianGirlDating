package com.lesgirls.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.lesgirls.utils.RetrofitImageAPI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;

/**
 * Created by victor on 13.07.16.
 */
public class DownloadImage{
    private static final String TAG = "DownloadImage";
    private final String token;
    private final long id;
    private final Context context;
    private final ImageView imageView;


    public DownloadImage(Context context, String token, long id, ImageView iv) {
        this.context = context;
        this.token = token;
        this.id = id;
        this.imageView = iv;
    }

    public void execute() throws IOException {
        Retrofit retrofit = UserContractFactory.getRetrofit();
        RetrofitImageAPI service = retrofit.create(RetrofitImageAPI.class);
        Call<ResponseBody> call = service.getImageDetails();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "Response came from server");
                    Bitmap bitmap = down(response.body());
                    if(bitmap != null)  Log.i(TAG, "Bitmap downloaded, "+bitmap.getWidth()+"x"+bitmap.getHeight());
                    else Log.i(TAG, "Bitmap is NULL ");
                    imageView.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }
    private Bitmap down(ResponseBody body) {

        try {
            Log.d("DownloadImage", "Reading and writing file");
            InputStream in = null;
            ByteArrayOutputStream out = null;
            byte[] data;
            try {
                in = body.byteStream();
                out = new ByteArrayOutputStream();
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                data = out.toByteArray();
            }
            catch (IOException e) {
                Log.d("DownloadImage",e.toString());
                return null;
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            Bitmap bMap = BitmapFactory.decodeByteArray(data,0,data.length);

            return bMap;

        } catch (IOException e) {
            Log.d("DownloadImage",e.toString());
            return null;
        }
    }
}
