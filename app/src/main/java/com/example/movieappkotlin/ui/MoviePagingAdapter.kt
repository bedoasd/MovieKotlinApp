package com.example.movieappkotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.BR
import com.example.movieappkotlin.databinding.MovieItemBinding
import com.example.movieappkotlin.response.Movie

class MoviePagingAdapter: PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIff_UTils) {



    var onclick:((String)->Unit)? =null

    companion object{
        val DIff_UTils=object : DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID==newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem==newItem
            }

        }
    }


    fun onMovieClick(listener:((String)->Unit)){
        onclick=listener
    }



    inner class MyViewHolder (val viewDataBinding:MovieItemBinding):RecyclerView.ViewHolder(viewDataBinding.root){

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data=getItem(position)

        holder.viewDataBinding.setVariable(BR.movie,data)

        holder.viewDataBinding.root.setOnClickListener {

            onclick?.let {
                it(data?.imdbID!!)
            }

        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

}





/*:PagingDataAdapter<Movie,MoviePagingAdapter.MyViewHolder>(Diff_UTil) {


    companion object {

        val Diff_UTil=object :DiffUtil.ItemCallback<Movie>(){
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem==newItem
            }

        }

    }

    inner class MyViewHolder(val viewDataBinding:MovieItemBinding):RecyclerView.ViewHolder(viewDataBinding.root){

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.movie,getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }
}*/