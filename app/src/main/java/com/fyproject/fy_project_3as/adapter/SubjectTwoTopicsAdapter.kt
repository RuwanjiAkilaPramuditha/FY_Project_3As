package com.example.recyclerviewfirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.SubjectOneTopics
import com.fyproject.fy_project_3as.model.SubjectTwoTopics

class SubjectTwoTopicsAdapter(private val subjectTwoTopicsList : ArrayList<SubjectTwoTopics>) : RecyclerView.Adapter<SubjectTwoTopicsAdapter.SubjectTwoTopicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectTwoTopicsAdapter.SubjectTwoTopicsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.subject_two_topics, parent, false)

        return SubjectTwoTopicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectTwoTopicsAdapter.SubjectTwoTopicsViewHolder, position: Int) {

        val subjectTwoTopics : SubjectTwoTopics = subjectTwoTopicsList[position]
        holder.subjectTwoTopicsSubtopic.text = subjectTwoTopics.subtopic
        holder.subjectTwoTopicsTopic.text = subjectTwoTopics.topic
        holder.subjectTwoTopicsHours.text = subjectTwoTopics.hours.toString()
        holder.subjectTwoTopicsCompleted.isChecked = subjectTwoTopics.completed!!

    }

    override fun getItemCount(): Int {
        return subjectTwoTopicsList.size
    }

    public class SubjectTwoTopicsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val subjectTwoTopicsSubtopic : TextView = itemView.findViewById(R.id.tvSubjectTwoTopicsSubtopic)
        val subjectTwoTopicsTopic : TextView = itemView.findViewById(R.id.tvSubjectTwoTopicsTopic)
        val subjectTwoTopicsHours : TextView = itemView.findViewById(R.id.tvSubjectTwoTopicsHours)
        val subjectTwoTopicsCompleted : CheckBox = itemView.findViewById(R.id.cbSubjectTwoTopicsCompleted)

    }
}