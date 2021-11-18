package com.example.android2021.kt03.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android2021.R
import com.example.android2021.kt05.*
import com.google.android.material.button.MaterialButton


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val tab01: MaterialButton = root.findViewById(R.id.tab_01)
        val tab02: MaterialButton = root.findViewById(R.id.tab_02)
        val tab03 :MaterialButton = root.findViewById(R.id.tab_03)
        val tab04 :MaterialButton = root.findViewById(R.id.tab_04)
        tab01.setOnClickListener {
            val intent = Intent(activity,Tab01Activity::class.java)
            startActivity(intent)
        }

        tab02.setOnClickListener {
            val intent = Intent(activity,TestServiceActivity::class.java)
            startActivity(intent)
        }

        tab03.setOnClickListener {
            val intent = Intent(activity,BroadcastActivity::class.java)
            startActivity(intent)
        }
        tab04.setOnClickListener {
            val intent = Intent(activity,ContentProviderActivity::class.java)
            startActivity(intent)
        }
        return root
    }
}