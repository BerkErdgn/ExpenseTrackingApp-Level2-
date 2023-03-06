package com.berke.expensevsincome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.berke.expensevsincome.databinding.ItemlistRecyclerRowBinding
import com.berke.expensevsincome.models.Post

class ListRecyclerAdapter (private val postList: ArrayList<Post>): RecyclerView.Adapter<ListRecyclerAdapter.PostHolder>(){

    class PostHolder(val binding: ItemlistRecyclerRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ItemlistRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.hintText.text = postList.get(position).hint
        holder.binding.moneyText.text = postList.get(position).price
        holder.binding.expenseOrIncomeText.text = postList.get(position).expenseOrIncome
    }

    override fun getItemCount(): Int {
        return  postList.size

    }
}