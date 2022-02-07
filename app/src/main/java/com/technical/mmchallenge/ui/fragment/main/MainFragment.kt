package com.technical.mmchallenge.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.technical.mmchallenge.R
import com.technical.mmchallenge.data.DataSource
import com.technical.mmchallenge.data.model.Album
import com.technical.mmchallenge.databinding.FragmentMainBinding
import com.technical.mmchallenge.domain.RepoImpl
import com.technical.mmchallenge.ui.activity.MainActivity
import com.technical.mmchallenge.ui.adapter.MainAdapter
import com.technical.mmchallenge.ui.viewmodel.MainViewModel
import com.technical.mmchallenge.ui.viewmodel.VMFactory
import com.technical.mmchallenge.vo.Resource

/**
 * Created by Luis Grez on 05 February 2022
 */


class MainFragment : Fragment(), MainAdapter.OnAlbumClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModelAlbums by viewModels<MainViewModel> {
        VMFactory(
            RepoImpl(DataSource())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Album List"
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModelAlbums.fetchAlbumList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.rvAlbums.visibility = View.INVISIBLE
                }
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.rvAlbums.adapter = MainAdapter(requireContext(), result.data, this)
                    binding.rvAlbums.visibility = View.VISIBLE
                    binding.rvAlbums.animation = AnimationUtils.loadAnimation(context, R.anim.zoom_anim)
                }
                is Resource.Failure -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.rvAlbums.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(), "Fail connection ${result.exception}", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onAlbumClick(album: Album) {
        val bundle = Bundle()
        bundle.putInt("albumId", album.id)
        findNavController().navigate(R.id.action_main_to_photos_list, bundle)
    }

    private fun setUpRecyclerView(){
        binding.rvAlbums.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvAlbums.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }
}