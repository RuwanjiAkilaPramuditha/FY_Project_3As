package com.fyproject.fy_project_3as.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewfirestore.SubjectOneTopicsAdapter
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.SubjectOneTopics
import com.google.firebase.firestore.*

class SubjectOneTopicsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var subjectOneTopicsArrayList: ArrayList<SubjectOneTopics>
    private lateinit var myAdapter: SubjectOneTopicsAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subject_one_topics)

        recyclerView = findViewById(R.id.subjectOneTopicsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        subjectOneTopicsArrayList = arrayListOf()

        myAdapter = SubjectOneTopicsAdapter(subjectOneTopicsArrayList)

        recyclerView.adapter = myAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("SubjectOneTopics").
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
                        subjectOneTopicsArrayList.add(dc.document.toObject(SubjectOneTopics::class.java))
                    }

                }

                myAdapter.notifyDataSetChanged()
            }
        })
    }
}