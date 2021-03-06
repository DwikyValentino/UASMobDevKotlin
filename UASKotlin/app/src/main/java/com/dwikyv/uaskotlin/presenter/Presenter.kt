package com.dwikyv.uaskotlin.presenter

import android.util.Log
import com.dwikyv.uaskotlin.model.ResultMahasiswa
import com.dwikyv.uaskotlin.model.ResultStatus
import com.dwikyv.uaskotlin.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter (val crudView: CrudView) {
    //Fungsi GetData
    fun getData(){
        NetworkConfig.getService().getData()
            .enqueue(object : retrofit2.Callback<ResultMahasiswa>{
                override fun onFailure(call: Call<ResultMahasiswa>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }
                override fun onResponse(call: Call<ResultMahasiswa>, response:
                Response<ResultMahasiswa>) {
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.mahasiswa
                            crudView.onSuccessGet(data)
                        } else{
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }
            })
    }
    //Add data
    fun addData(name : String, nim : String, alamat : String){
        NetworkConfig.getService()
            .addStaff(name, nim, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response:
                Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    }else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }
            })
    }
    //Hapus Data
    fun hapusData(id: String?){
        NetworkConfig.getService()
            .deleteStaff(id)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response:
                Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDelete(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDelete(response.body()?.pesan ?: "")
                    }
                }
            })
    }
    //Update Data
    fun updateData(id: String, name: String, nim: String, alamat: String){
        NetworkConfig.getService()
            .updateStaff(id, name, nim, alamat)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response:
                Response<ResultStatus>) {
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}