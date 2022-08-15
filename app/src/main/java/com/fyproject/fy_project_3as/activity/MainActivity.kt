package com.fyproject.fy_project_3as.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.adapter.DashboardAdapter
import com.fyproject.fy_project_3as.model.DashboadItem

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var arrayList: ArrayList<DashboadItem>? = null
    private var gridView: GridView? = null
    private var dashboardAdapter: DashboardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view_list)
        arrayList = ArrayList()
        arrayList = setDataList()
        dashboardAdapter = DashboardAdapter(applicationContext,arrayList!!)
        gridView?.adapter = dashboardAdapter
        gridView?.onItemClickListener = this
    }

    private fun setDataList(): ArrayList<DashboadItem>? {

        arrayList?.add(DashboadItem(R.drawable.syllabus,"Syllabus"))
        arrayList?.add(DashboadItem(R.drawable.pastpaper,"Past Papers"))
        arrayList?.add(DashboadItem(R.drawable.quiz,"Quizzes"))
        arrayList?.add(DashboadItem(R.drawable.milestone,"Milestone"))
        arrayList?.add(DashboadItem(R.drawable.stats, "Stats"))
        arrayList?.add(DashboadItem(R.drawable.profile,"Profile"))

        return arrayList

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item:DashboadItem = arrayList!!.get(position)
        Toast.makeText(applicationContext, item.name, Toast.LENGTH_SHORT).show()
    }
}