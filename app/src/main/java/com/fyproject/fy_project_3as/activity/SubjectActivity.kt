package com.fyproject.fy_project_3as.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.fyproject.fy_project_3as.R

class SubjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject)

        initComponent()
    }

    var pps1: Button? = null
    var pps2: Button? = null
    var pps3: Button? = null

    private fun initComponent() {
        pps1 = findViewById(R.id.btn_pp_it)
        pps2 = findViewById(R.id.btn_pp_phy)
        pps3 = findViewById(R.id.btn_pp_chem)

        pps1?.setOnClickListener(View.OnClickListener {
            val i = Intent(this, SubjectOneTopicsActivity::class.java)
            this.startActivity(i)
        })

        pps2?.setOnClickListener(View.OnClickListener {
            val i = Intent(this, SubjectTwoTopicsActivity::class.java)
            this.startActivity(i)
        })

        pps3?.setOnClickListener(View.OnClickListener {
            val i = Intent(this, SubjectThreeTopicsActivity::class.java)
            this.startActivity(i)
        })
    }
}