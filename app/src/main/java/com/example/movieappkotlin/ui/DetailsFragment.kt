package com.example.movieappkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieappkotlin.R
import com.example.movieappkotlin.databinding.FragmentDetailsBinding
import com.example.movieappkotlin.utils.Result
import com.example.movieappkotlin.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

   lateinit var  binding:FragmentDetailsBinding
   val args :DetailsFragmentArgs by navArgs() //==intentExtra

    val  movieViewModel:MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        binding.backPress.setOnClickListener{

            findNavController().popBackStack()
        }

        movieViewModel.getMovieDetails(args.imdbId!!)

        movieViewModel.movieDetails.observe(viewLifecycleOwner){
            when(it.getContentIfNotHandled()?.status)
            {
                Result.Status.SUCCESS -> {
                    binding.detailsProgress.visibility=View.GONE

                    binding.movieDetails=it.peekContent().data
                }
                Result.Status.LOADING -> {
                    binding.detailsProgress.visibility=View.GONE

                }

                Result.Status.ERROR -> {
                    binding.detailsProgress.visibility=View.GONE

                }


                else -> {}
            }

        }


    }




}