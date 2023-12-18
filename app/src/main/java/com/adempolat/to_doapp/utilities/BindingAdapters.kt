package com.adempolat.to_doapp.utilities

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adempolat.to_doapp.R
import com.adempolat.to_doapp.model.Priority
import com.adempolat.to_doapp.model.ToDoModel
import com.adempolat.to_doapp.ui.home.HomeListAdapter
import com.adempolat.to_doapp.ui.home.ToDoClickListener

@BindingAdapter("setItemTodoPriorityTint")
fun setItemTodoPriorityTint(imageView: ImageView,priority: Priority?){
    val context=imageView.context
    val color = when(priority){
        Priority.HIGH -> R.color.priority_high
        Priority.MEDIUM -> R.color.md_theme_light_secondary
        else -> R.color.seed
    }

    ImageViewCompat.setImageTintList(
        imageView,
        ColorStateList.valueOf(ContextCompat.getColor(context,color)))
}

@BindingAdapter("toDoList","setOnClickListener","searchQuery","searchTodoList")
fun setHomeRecyclerViewAdapter(recyclerView: RecyclerView,
                               list: List<ToDoModel>?,
                               toDoClickListener: ToDoClickListener,
                               searchQuery:String,
                               searchList: List<ToDoModel>?){
    recyclerView.apply {
        if (this.adapter==null){
            adapter= HomeListAdapter(toDoClickListener).apply {
                submitList(
                if (searchQuery.isEmpty()){
                    list
                }else{
                    searchList
                }
            )
            }
        }else{
            (this.adapter as HomeListAdapter).submitList(
                if (searchQuery.isEmpty()){
                    list
                    }else{
                        searchList
                }
                    )
        }
    }
}

