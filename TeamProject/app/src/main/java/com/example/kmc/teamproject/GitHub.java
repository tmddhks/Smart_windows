package com.example.kmc.teamproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by KMC on 2018-02-19.
 */

public interface GitHub {
    // GET/POST/DELETE/PUT 메소드들을 인터페이스에 구현하여 사용할 수 있다.
    @GET("/{owner}/window/")
    // JSON Array를 리턴하므로 List<>가 되었다
    Call<List<Window>> windows(
            // param 값으로 들어가는 것들이다
            @Path("owner") String owner);

}
