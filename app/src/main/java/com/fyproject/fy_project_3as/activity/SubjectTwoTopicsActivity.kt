package com.fyproject.fy_project_3as.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewfirestore.SubjectOneTopicsAdapter
import com.example.recyclerviewfirestore.SubjectTwoTopicsAdapter
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.SubjectOneTopics
import com.fyproject.fy_project_3as.model.SubjectTwoTopics
import com.google.firebase.firestore.*

class SubjectTwoTopicsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var subjectTwoTopicsArrayList: ArrayList<SubjectTwoTopics>
    private lateinit var myAdapter: SubjectTwoTopicsAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_two_topics)

        recyclerView = findViewById(R.id.subjectTwoTopicsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        subjectTwoTopicsArrayList = arrayListOf()

        myAdapter = SubjectTwoTopicsAdapter(subjectTwoTopicsArrayList)

        recyclerView.adapter = myAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("SubjectTwoTopics").
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if (error !=  null) {

                    Log.e("Firestore Error", error.message.toString())
                    return

                }

                for (dc : DocumentChange in value?.documentChanges!!) {

                    if (dc.type == DocumentChange.Type.ADDED) {
                        subjectTwoTopicsArrayList.add(dc.document.toObject(SubjectTwoTopics::class.java))
                    }

                }

                myAdapter.notifyDataSetChanged()
            }
        })
    }
}