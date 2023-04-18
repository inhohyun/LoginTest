package com.ihh.logintest

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    //실제 서버 주소는 retrofit 객체를 만들 때 사용
    //FormUrlEncoded : 서버에서 정확한 값을 얻어오기 위한 encode
    @FormUrlEncoded
    @POST("서버의 엔드포인트 URL")
    //서버에 id, pw 값 보내기
   fun requestLogin(
        @Field("userid") userid: String,
        @Field("userpw") userpw: String
    ) : Call<Login> // 서버에서 받을 값을 data class의 변수에 저장
}