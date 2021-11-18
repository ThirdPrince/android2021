package com.example.android2021.kt03.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android2021.R
import com.example.android2021.kt04.*
import com.example.android2021.utils.EasyLog
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
   慕课网学习 Fragment
 */
class StudyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recycler_view :RecyclerView

    private lateinit var courseAdapter: CourseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_study, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view = view.findViewById(R.id.recycler_view)
        recycler_view.layoutManager = LinearLayoutManager(activity)

        val apiService = HiRetrofit.create(ApiService::class.java)
        apiService.getStudy().enqueue(object :Callback<List<CourseItem>>{
            override fun onResponse(call: Call<List<CourseItem>>, response: Response<List<CourseItem>>) {
                EasyLog.e("study"," = ${response.body().toString()}")
                courseAdapter = CourseAdapter(context!!,
                    response.body()!! as MutableList<CourseItem>
                )
                recycler_view.adapter = courseAdapter

            }

            override fun onFailure(call: Call<List<CourseItem>>, t: Throwable) {

            }

        })

        view.findViewById<MaterialButton>(R.id.tab_add_course).setOnClickListener {

            val course = CourseItem(
                "Android学习基础",
                "https://www.songyubao.com/static/book/assets/icon-android.jpeg",
                "100%",
                "Android RecyclerView基础学习"
            )
            courseAdapter.addCourse(course)
        }
        view.findViewById<MaterialButton>(R.id.tab_delete_course).setOnClickListener{
            courseAdapter.deleteCourse(0)
        }

        view.findViewById<MaterialButton>(R.id.tab_update_course).setOnClickListener{
            courseAdapter.updateCourse(0, "90%")
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        EasyLog.e(TAG,"onHiddenChanged = $hidden")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}