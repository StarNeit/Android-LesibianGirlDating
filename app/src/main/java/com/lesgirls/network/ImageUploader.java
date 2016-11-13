package com.lesgirls.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;


public class ImageUploader extends BaseLoader {
    private static final String TAG = "ImageUploader";
    private final String token;

    private final File file;

    public ImageUploader(Context context, String token, String sFile) {
        super(context);
        this.token = token;
        this.file =new File(sFile);
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        //MultipartBody.Part userToken = MultipartBody.Part.createFormData("data[token]", this.token);
        MultipartBody.Part userToken = MultipartBody.Part.createFormData("token", this.token);
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), this.file);

        MultipartBody.Part photo = MultipartBody.Part.createFormData("data[Attachment][image]", this.file.getName(), imageBody);
        Call<JsonObject> call = service.addImage(userToken, photo);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new ImageUploaderResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
