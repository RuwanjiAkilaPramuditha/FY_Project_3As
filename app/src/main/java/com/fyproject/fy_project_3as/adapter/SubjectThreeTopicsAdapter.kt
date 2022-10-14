package com.example.recyclerviewfirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fyproject.fy_project_3as.R
import com.fyproject.fy_project_3as.model.SubjectThreeTopics


class SubjectThreeTopicsAdapter(private val subjectThreeTopicsList : ArrayList<SubjectThreeTopics>) : RecyclerView.Adapter<SubjectThreeTopicsAdapter.SubjectThreeTopicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectThreeTopicsAdapter.SubjectThreeTopicsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.subject_three_topics, parent, false)

        return SubjectThreeTopicsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectThreeTopicsAdapter.SubjectThreeTopicsViewHolder, position: Int) {

        val subjectThreeTopics : SubjectThreeTopics = subjectThreeTopicsList[position]
        holder.subjectThreeTopicsSubtopic.text = subjectThreeTopics.subTopic
        holder.subjectThreeTopicsTopic.text = subjectThreeTopics.topic
        holder.subjectThreeTopicsHours.text = subjectThreeTopics.hours.toString()
        holder.subjectThreeTopicsCompleted.isChecked = subjectThreeTopics.completed!!

    }

    override fun getItemCount(): Int {
        return subjectThreeTopicsList.size
    }

    public class SubjectThreeTopicsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val subjectThreeTopicsSubtopic : TextView = itemView.findViewById(R.id.tvSubjectThreeTopicsSubtopic)
        val subjectThreeTopicsTopic : TextView = itemView.findViewById(R.id.tvSubjectThreeTopicsTopic)
        val subjectThreeTopicsHours : TextView = itemView.findViewById(R.id.tvSubjectThreeTopicsHours)
        val subjectThreeTopicsCompleted : CheckBox = itemView.findViewById(R.id.cbSubjectThreeTopicsCompleted)

    }
}
