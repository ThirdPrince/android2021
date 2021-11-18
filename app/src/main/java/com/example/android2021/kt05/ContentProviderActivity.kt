package com.example.android2021.kt05

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android2021.R
import java.util.*


class ContentProviderActivity : AppCompatActivity() {


    private lateinit var contactViewModel: ContactViewModel

    private lateinit var contactAdapter: ContactAdapter

    private val recyclerView:RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider2)
        setSupportActionBar(findViewById(R.id.toolbar))
        title = "添加联系人"
        contactViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        ).get(ContactViewModel::class.java)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                1024
            )
        } else {
            getContacts()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        contactViewModel.lvContact.observe(this, Observer {
            contactAdapter = ContactAdapter(it)
            recyclerView.adapter = contactAdapter
            contactAdapter.onLongItemClickListener = object : OnLongItemClickListener {
                override fun onLongClick(contactBean: ContactBean) {

                    AlertDialog.Builder(this@ContentProviderActivity)
                        .setMessage("你确定要删除吗?")
                        .setPositiveButton("确定"
                        ) { dialog, which ->
                            contactViewModel.deleteContactById( contactBean.id)
                        }
                        .show()

                }

            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1024 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            getContacts()
        }
    }

    private fun getContacts(){
        contactViewModel.getContact()
    }


    @Override
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        menuInflater.inflate(R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_contact -> {
                contactViewModel.addContact()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}