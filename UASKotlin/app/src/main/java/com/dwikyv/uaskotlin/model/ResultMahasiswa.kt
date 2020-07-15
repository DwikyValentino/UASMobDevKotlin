package com.dwikyv.uaskotlin.model

import com.google.gson.annotations.SerializedName

data class ResultMahasiswa (
    @field:SerializedName("pesan")
    val pesan: String? = null,
    @field:SerializedName("mahasiswa")
    val mahasiswa: List<DataItem>? = null,
    @field:SerializedName("status")
    val status: Int? = null
)
