package com.example.android2021.kt03

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android2021.R
import com.example.android2021.kt04.ApiService
import com.example.android2021.kt04.HiOKHttp
import com.example.android2021.kt04.HiRetrofit
import com.example.android2021.kt04.UserResponse
import com.example.android2021.utils.EasyLog
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class MainTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_study
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // HiOKHttp.getByAsyn("/user/query?userId= 1600932269")
        val map = mutableMapOf<String,Int>()
        map["tagId"] = 71
        map["userId"] = 1600932269
        //HiOKHttp.postByFormBody("/tag/toggleTagFollow",map)
        //HiOKHttp.postJson("/welcome")
        //HiOKHttp.postText()

        val apiService = HiRetrofit.create(ApiService::class.java)
        apiService.queryUser("1600932269").enqueue(object :retrofit2.Callback<UserResponse>{

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                EasyLog.e("retrofit","rsp = ${response.body()?.data?.user?.name}")

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }

        })
    }
}


