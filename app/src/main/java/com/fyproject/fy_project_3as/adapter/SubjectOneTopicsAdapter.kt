package com.example.recyclerviewfirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.SubjectOneTopics

class SubjectOneTopicsAdapter(private val subjectOneTopicsList : ArrayList<SubjectOneTopics>) : RecyclerView.Adapter<SubjectOneTopicsAdapter.SubjectOneTopicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectOneTopicsAdapter.SubjectOneTopicsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.subject_one_topics, parent, false)

        return SubjectOneTopicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectOneTopicsAdapter.SubjectOneTopicsViewHolder, position: Int) {

        val subjectOneTopics : SubjectOneTopics = subjectOneTopicsList[position]
        holder.subjectOneTopicsSubtopic.text = subjectOneTopics.subtopic
        holder.subjectOneTopicsTopic.text = subjectOneTopics.topic
        holder.subjectOneTopicsHours.text = subjectOneTopics.hours.toString()
        holder.subjectOneTopicsCompleted.isChecked = subjectOneTopics.completed!!

    }

    override fun getItemCount(): Int {
        return subjectOneTopicsList.size
    }

    public class SubjectOneTopicsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val subjectOneTopicsSubtopic : TextView = itemView.findViewById(R.id.tvSubjectOneTopicsSubtopic)
        val subjectOneTopicsTopic : TextView = itemView.findViewById(R.id.tvSubjectOneTopicsTopic)
        val subjectOneTopicsHours : TextView = itemView.findViewById(R.id.tvSubjectOneTopicsHours)
        val subjectOneTopicsCompleted : CheckBox = itemView.findViewById(R.id.cbSubjectOneTopicsCompleted)

    }
}