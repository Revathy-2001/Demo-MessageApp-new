package com.example.demomessageapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FreshApi {

    @Headers({
            "Authorization: JWTAuth token=eyJhbGciOiJkaXIiLCJlbmMiOiJBMTI4R0NNIn0..Zl9plpeg9eYryfyw.o3EYxVXqeNL9vBQ7tJqo7uGbET1-6Zf0ljVbV3LA_MreIhosf1ayf1gGpvt4L4djBeYbs-vleOCTrNuyHnS4_mcctGbBG_B3m3oygKvNkzQpcmx2FBqm5W7MISknPee6Ki4s2R4DmOPwZoshPzzxudTS86011goAA4mJewj32-ybp7fQ8a58i8V4hxKCoyt0cdK-pMJoTMytvtYuure5mo4vHI6MablrAF4jemF3zm8c.U6wnnSky3GSKGI6-oZJzWQ",
            "User-Agent: freshteam_native_mobile/1.0"
    })
    @GET("users") //http get request. The string inside the parameter is the API name.
    Call<Users> getUsers(
            @Query("page") int page,
            @Query("per_page") int per_page
    );
}


//Type of a call should be list.