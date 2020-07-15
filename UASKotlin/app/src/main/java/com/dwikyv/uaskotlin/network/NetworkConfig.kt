package com.dwikyv.uaskotlin.network

import com.dwikyv.uaskotlin.model.ResultStatus
import com.dwikyv.uaskotlin.model.ResultMahasiswa
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }
    //Retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()

            .baseUrl("https://kotlinandroid.000webhostapp.com/index.php/ServerApi/")
            .client(getInterceptor())

            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() =
        getRetrofit().create(StaffService::class.java)
}
interface StaffService{
    //Fungsi Create Data
    @FormUrlEncoded
    @POST("addMahasiswa")
    fun addStaff(@Field("name") name : String,
                 @Field("nim") nim : String,
                 @Field("alamat") alamat : String) :
            Call<ResultStatus>
    //Fungsi Get Data
    @GET("getDataMahasiswa")
    fun getData() : Call<ResultMahasiswa>
    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("deleteMahasiwa")
    fun deleteStaff(@Field("id") id: String?) :
            Call<ResultStatus>
    //Fungsi Update Data
    @FormUrlEncoded
    @POST("updateMahasiswa")
    fun updateStaff(@Field("id") id: String,
                    @Field("name") name: String,
                    @Field("nim") nim : String,
                    @Field("alamat") alamat : String) :
            Call<ResultStatus>
}