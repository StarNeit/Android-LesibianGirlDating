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


public class AvatarUpdater extends BaseLoader {
    private static final String TAG = "AvatarUpdater";
    private final long userID;

    private final File file;

    public AvatarUpdater(Context context, long userID, String sFile) {
        super(context);
        this.userID = userID;
        this.file =new File(sFile);
    }

    @Override
    protected Response apiCall() throws IOException {
        UserContract service = UserContractFactory.getUserContract();
        MultipartBody.Part userIDPart = MultipartBody.Part.createFormData("data[Attachment][user_id]", String.valueOf(this.userID));
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), this.file);

        MultipartBody.Part avatar = MultipartBody.Part.createFormData("data[Attachment][image]", this.file.getName(), imageBody);
        Call<JsonObject> call = service.updateAvatar(userIDPart, avatar);
        JsonObject result = call.execute().body();
        Log.i(TAG, result.toString());
        return new AvatarUpdaterResponse().setRequestResult(RequestResult.SUCCESS).setAnswer(result);
    }
}
