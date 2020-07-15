package com.dwikyv.uaskotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable{
    @field:SerializedName("mahasiswa_name")
    val mahasiswaName: String? = null
    @field:SerializedName("mahasiswa_id")
    val mahasiswaId: String? = null
    @field:SerializedName("mahasiswa_nim")
    val mahasiswaNim: String? = null
    @field:SerializedName("mahasiswa_alamat")
    val mahasiswaAlamat: String? = null
}
