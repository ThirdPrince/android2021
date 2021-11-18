package com.example.android2021.kt05

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.example.android2021.R
import com.example.android2021.kt03.gallery.GalleryFragment
import com.example.android2021.kt03.ui.StudyFragment
import com.example.android2021.kt03.ui.dashboard.DashboardFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import java.lang.IllegalStateException

/**
 * 自定义实现多Tab底部导航栏页面结构(1)
 *
 * switchFragment 更加简洁
 *
 */

const val TAB01 = 0
const val TAB02 = 1
const val TAB03 = 2

class Tab01Activity : AppCompatActivity() {


    private val materialButtonToggleGroup: MaterialButtonToggleGroup by lazy {
        findViewById<MaterialButtonToggleGroup>(R.id.toggle_group)
    }

    private var dashboardFragment: DashboardFragment? = null
    private var studyFragment: StudyFragment? = null

    private var galleryFragment: GalleryFragment? = null

    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab01)
        materialButtonToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->

            group.forEachIndexed { index, view ->
                val button = view as MaterialButton
                if (button.id == checkedId) {
                    button.setTextColor(Color.RED)
                    button.iconTint = ColorStateList.valueOf(Color.RED)
                    switchFragment(index)
                } else {
                    button.setTextColor(Color.BLACK)
                    button.iconTint = ColorStateList.valueOf(Color.BLACK)
                }
            }


        }
        materialButtonToggleGroup.check(R.id.tab1)
        // switchFragment(TAB01)
    }

    private fun switchFragment(index: Int) {
        val fragment = when (index) {
            TAB01 -> {
                if (dashboardFragment == null) {
                    dashboardFragment = DashboardFragment()
                }
                dashboardFragment
            }
            TAB02 -> {
                if (studyFragment == null) {
                    studyFragment = StudyFragment()
                }
                studyFragment
            }

            TAB03 -> {
                if (galleryFragment == null) {
                    galleryFragment = GalleryFragment()
                }
                galleryFragment
            }
            else -> {
                throw IllegalStateException("下标不符合预期")
            }
        } ?:return
        val ft = supportFragmentManager.beginTransaction()
        if (!fragment?.isAdded) {
            ft.add(R.id.container,fragment)
        }
        currentFragment?.let {
            ft.hide(it!!)
        }
        currentFragment = fragment
        ft.show(currentFragment!!)
        ft.commitAllowingStateLoss()
    }
}