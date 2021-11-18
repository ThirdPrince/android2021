package com.example.android2021.kt03.recy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android2021.R

/**
 * RecyclerView demo
 */
class RecyclerViewFragment : Fragment(R.layout.fragment_recy_view) {


    private lateinit var rcy_view: RecyclerView

    private val maAdapter: MyAdapter by lazy {
        MyAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcy_view = view.findViewById(R.id.rcy_view)
        // initRcyVertical()
        //initRcyHorizontal()
        initRcyGrid()
        //initRcyStagger()
    }

    /**
     * Vertical
     */
    private fun initRcyVertical() {
        val linearLayoutManager = LinearLayoutManager(activity)
        rcy_view.layoutManager = linearLayoutManager
        rcy_view.adapter = maAdapter
    }

    /**
     * Horizontal
     */
    private fun initRcyHorizontal() {
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rcy_view.layoutManager = linearLayoutManager
        rcy_view.adapter = maAdapter
    }

    /**
     * Grid
     */
    private fun initRcyGrid() {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        rcy_view.layoutManager = gridLayoutManager
        rcy_view.adapter = maAdapter
    }

    /**
     * Grid
     */
    private fun initRcyStagger() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        rcy_view.layoutManager = staggeredGridLayoutManager
        rcy_view.adapter = maAdapter
    }


}