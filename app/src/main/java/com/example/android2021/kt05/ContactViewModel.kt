package com.example.android2021.kt05

import android.app.Application
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.android2021.utils.EasyLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Title: $
 * @Package $
 * @Description: 对联系人的操作viewModel
 * @author $
 * @date $
 * @version V1.0
 */
class ContactViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "ContactViewModel"

    private val _lvContact = MutableLiveData<List<ContactBean>>()
    val lvContact: LiveData<List<ContactBean>>
        get() = _lvContact

    fun getContact() {
        viewModelScope.launch {
            val contactList = asynGetContact()
            _lvContact.postValue(contactList)
        }
    }

    fun addContact() {

        viewModelScope.launch {
            val values = ContentValues()
            withContext(Dispatchers.IO) {

                /**
                 * 首先向RawContacts.Content_URI 执行一个空值插入，目的是获得系统返回的rawContactId
                 * 这时后面插入data 表的数据，才能使插入的在通讯录里面可见
                 */
                val contentResolver = getApplication<Application>().contentResolver
                val rawContactUri: Uri =
                    contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)!!
                val rawContactId = ContentUris.parseId(rawContactUri)
                // 往data 表里写入姓名数据
                values.clear()
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                values.put(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE
                )
                values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "李四")
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)


                //插入电话数据
                values.clear()
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                values.put(
                    ContactsContract.Data.MIMETYPE,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                )
                values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "18796017664")
                values.put(
                    ContactsContract.CommonDataKinds.Phone.TYPE,
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
                )
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
            }
            val contactList = asynGetContact()
            _lvContact.postValue(contactList)
        }


    }

    fun deleteContactById(id: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val contentResolver = getApplication<Application>().contentResolver
                contentResolver.delete(
                    ContactsContract.RawContacts.CONTENT_URI,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id)
                );
            }
            val contactList = asynGetContact()
            _lvContact.postValue(contactList)
        }


    }

    @WorkerThread
    suspend fun asynGetContact(): List<ContactBean> = withContext(Dispatchers.IO) {

        val contractList = mutableListOf<ContactBean>()
        val resolver = getApplication<Application>().contentResolver
        val uri = Uri.parse("content://com.android.contacts/data/phones")
        val cursor = resolver.query(uri, null, null, null, null)!!
        while (cursor.moveToNext()) {
            //获取联系人姓名,手机号码

            val cId: String =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
            val cName: String =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val cNum: String =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            EasyLog.e(TAG, "id:$cId")
            EasyLog.e(TAG, "姓名:$cName")
            EasyLog.e(TAG, "号码:$cNum")
            EasyLog.e(TAG, "======================")
            val contactBean = ContactBean(cId, cName, cNum)
            contractList.add(contactBean)

        }
        cursor.close()

        return@withContext contractList

    }


}