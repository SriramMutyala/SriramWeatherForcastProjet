package com.example.sriram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sriram.Model.ItemList

class WeatherItemListAdapter(private val itemList: List<ItemList>,
                             private val adapterOnClick:(ItemList)-> Unit ):RecyclerView.Adapter<WeatherItemListAdapter.WeatherListViewHolder> (){
   inner class WeatherListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
       private var leftText :TextView?=null
       private var rightText :TextView?=null
       private var viewItem :RelativeLayout?=null
       init {
           leftText = itemView.findViewById(R.id.left_text)
           rightText = itemView.findViewById(R.id.right_text)
           viewItem = itemView.findViewById(R.id.view_item)
       }
       fun bindView(itemList: ItemList, position: Int) {
           leftText?.text = itemList.weather?.get(0)?.main
           rightText?.text = itemList.main?.temp
           viewItem?.setOnClickListener {
                   adapterOnClick(itemList)
               }


       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_view_item,parent,false)
        return WeatherListViewHolder(view)
    }


    override fun onBindViewHolder(holder: WeatherListViewHolder, position: Int) {
        holder.bindView(itemList[position],position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}
