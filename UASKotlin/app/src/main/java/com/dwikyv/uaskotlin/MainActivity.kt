package com.dwikyv.uaskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dwikyv.uaskotlin.adapter.DataAdapter
import com.dwikyv.uaskotlin.model.DataItem
import com.dwikyv.uaskotlin.presenter.CrudView
import com.dwikyv.uaskotlin.presenter.Presenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this)
        presenter.getData()
        btnTambah.setOnClickListener {
            startActivity<UpdateAddActivity>()
            finish()
        }
    }
    override fun onSuccessGet(data: List<DataItem>?) {
        rvCategory.adapter = DataAdapter(data,object :
            DataAdapter.onClickItem{
            override fun clicked(item: DataItem?) {
                startActivity<UpdateAddActivity>("dataItem" to item)
            }
            override fun delete(item: DataItem?) {
                presenter.hapusData(item?.mahasiswaId)
                startActivity<MainActivity>()
                finish()
            }
        })
    }
    override fun onFailedGet(msg: String) {
    }
    override fun onSuccessDelete(msg: String) {
        presenter.getData()
    }
    override fun onErrorDelete(msg: String) {
        alert {
            title = "Error Delete Data"
        }.show()
    }
    override fun successAdd(msg: String) {
    }
    override fun errorAdd(msg: String) {
    }
    override fun onSuccessUpdate(msg: String) {
    }
    override fun onErrorUpdate(msg: String) {
    }
}
