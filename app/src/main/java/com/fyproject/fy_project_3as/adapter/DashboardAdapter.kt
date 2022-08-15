package com.fyproject.fy_project_3as.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.DashboadItem

class DashboardAdapter (var context: Context, private var arrayList: ArrayList<DashboadItem>):
    BaseAdapter() {

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view: View = View.inflate(context, R.layout.card_view_grid_item, null)
        var icons: ImageView = view.findViewById(R.id.icons)
        var names: TextView = view.findViewById(R.id.name_tv)

        var listItem: DashboadItem = arrayList.get(position)

        icons.setImageResource(listItem.icons!!)
        names.text = listItem.name

        return view
    }
}

