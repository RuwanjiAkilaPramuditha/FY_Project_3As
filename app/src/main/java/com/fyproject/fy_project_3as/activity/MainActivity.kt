package com.fyproject.fy_project_3as.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.adapter.DashboardAdapter
import com.fyproject.fy_project_3as.model.DashboadItem

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var arrayList: ArrayList<DashboadItem>? = null
    private var gridView: GridView? = null
    private var dashboardAdapter: DashboardAdapter? = null

   // var syllabus: TextView? = null
   // var pastpaper: TextView? = null
   // var quizzes: TextView? = null
   // var milestone: TextView? = null
  //  var stats: TextView? = null
  //  var profile: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid_view_list)
        arrayList = ArrayList()
        arrayList = setDataList()
        dashboardAdapter = DashboardAdapter(applicationContext,arrayList!!)
        gridView?.adapter = dashboardAdapter
        gridView?.onItemClickListener = this

     //   initComponent();
    }
//
//    @SuppressLint("ResourceType")
//    private fun initComponent() {
//        syllabus = findViewById(R.drawable.syllabus)
//        pastpaper = findViewById(R.drawable.pastpaper)
//        quizzes = findViewById(R.drawable.quiz)
//        milestone = findViewById(R.drawable.milestone)
//        stats = findViewById(R.drawable.stats)
//        profile = findViewById(R.drawable.profile)

 //       syllabus?.setOnClickListener(View.OnClickListener {
    //          val i = Intent(this, SyllabusActivity::class.java)
  //          this.startActivity(i)
 //       })

 //   }
//
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
        if (item.name.equals("Syllabus")) {
            val intent = Intent(this, TopicActivity::class.java)
            startActivity(intent)
        } else if (item.name.equals("Past Papers")) {
            val intent = Intent(this, SubjectActivity::class.java)
            startActivity(intent)
        } else if (item.name.equals("Stats")) {
            val intent = Intent(this, StatsActivity::class.java)
            startActivity(intent)
        } else if (item.name.equals("Profile")) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
//        Toast.makeText(applicationContext, item.name, Toast.LENGTH_SHORT).show()
    }
}