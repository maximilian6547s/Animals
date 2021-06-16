package com.maximcuker.animal.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maximcuker.animal.R
import com.maximcuker.animal.model.Animal
import com.maximcuker.animal.util.getProgressDrawable
import com.maximcuker.animal.util.loadImage
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animalList: ArrayList<Animal>):
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList: List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal,parent,false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalName.text = animalList[position].name
        holder.view.animalImage.loadImage(animalList[position].imageUrl,
            getProgressDrawable(holder.view.context))
    }

    override fun getItemCount(): Int = animalList.size

    class AnimalViewHolder(var view: View): RecyclerView.ViewHolder(view) {

    }
}