package util;

import com.maro.gadspracticeprojectmaroafenogho.Hours;
import com.maro.gadspracticeprojectmaroafenogho.SkillIq;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/api/hours")
    Call<List<Hours>> getHours();

    @GET("/api/skilliq")
    Call<List<SkillIq>> getSkillIq();

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
//    @POST("/forms/u/0/d/e/1FAIpQLSckAzJb4pkwGkwbV92VenEkOMItNA8iDi8yGDrBRuFZyS9yng/formResponse")
    @POST("/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> submitProject(
            @Field("entry.2061166757")String email,
            @Field("entry.419693947")String firstName,
            @Field("entry.426541051")String lastName,
            @Field("entry.980124250")String projectLink
    );

}
