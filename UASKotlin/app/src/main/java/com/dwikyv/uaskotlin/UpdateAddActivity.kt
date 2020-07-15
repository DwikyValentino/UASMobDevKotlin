package com.dwikyv.uaskotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dwikyv.uaskotlin.model.DataItem
import com.dwikyv.uaskotlin.presenter.CrudView
import com.dwikyv.uaskotlin.presenter.Presenter
import kotlinx.android.synthetic.main.activity_update_add.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

@Suppress("SENSELESS_COMPARISON")
class UpdateAddActivity : AppCompatActivity(), CrudView {
    private lateinit var presenter: Presenter
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)
        presenter = Presenter(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        if (itemDataItem == null){
            btnAction.text = "Tambah"
            btnAction.onClick {
                presenter.addData(
                    etName.text.toString(),
                    etNim.text.toString(),
                    etAlamat.text.toString())
            }
        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etName.setText(item?.mahasiswaName.toString())
            etNim.setText(item?.mahasiswaNim.toString())
            etAlamat.setText(item?.mahasiswaAlamat.toString())
            btnAction.onClick {
                presenter.updateData(
                    item?.mahasiswaId ?: "",
                    etName.text.toString(),
                    etNim.text.toString(),
                    etAlamat.text.toString())
                finish()
            }
        }
    }
    override fun successAdd(msg: String) {
        startActivity<MainActivity>()
        finish()
    }
    override fun errorAdd(msg: String) {}
    override fun onSuccessUpdate(msg: String) {
        startActivity<MainActivity>()
        finish()
    }
    override fun onErrorUpdate(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}
