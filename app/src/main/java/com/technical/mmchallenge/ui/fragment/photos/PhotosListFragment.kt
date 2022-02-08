package com.technical.mmchallenge.ui.fragment.photos

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
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.databinding.FragmentPhotosListBinding
import com.technical.mmchallenge.domain.RepoImpl
import com.technical.mmchallenge.ui.activity.MainActivity
import com.technical.mmchallenge.ui.adapter.PhotosAdapter
import com.technical.mmchallenge.ui.viewmodel.PhotosDetailViewModel
import com.technical.mmchallenge.ui.viewmodel.VMFactory
import com.technical.mmchallenge.vo.Resource

/**
 * Created by Luis Grez on 06 February 2022
 */


class PhotosListFragment : Fragment(), PhotosAdapter.OnPhotoClickListener {

    private var _binding: FragmentPhotosListBinding? = null
    private val binding get() = _binding!!

    private val viewModelPhotos by viewModels<PhotosDetailViewModel> {
        VMFactory(
            RepoImpl(DataSource())
        )
    }

    private var albumId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            albumId = it.getInt("albumId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Photos List"
        _binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        viewModelPhotos.fetchPhotosList(albumId!!).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.rvPhotos.visibility = View.INVISIBLE
                }
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.rvPhotos.adapter = PhotosAdapter(requireContext(), result.data, this)
                    binding.rvPhotos.visibility = View.VISIBLE
                    binding.rvPhotos.animation =
                        AnimationUtils.loadAnimation(context, R.anim.zoom_anim)
                }
                is Resource.Failure -> {
                    binding.progressCircular.visibility = View.INVISIBLE
                    binding.rvPhotos.visibility = View.INVISIBLE
                    Toast.makeText(requireContext(),
                        "Fail connection ${result.exception}",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    override fun onPhotoClick(photo: Photos) {
        val bundle = Bundle()
        bundle.putParcelable("photo", photo)
        findNavController().navigate(R.id.action_photos_list_to_photo, bundle)
    }

    private fun setUpRecyclerView() {
        binding.rvPhotos.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.rvPhotos.addItemDecoration(DividerItemDecoration(requireContext(),
            DividerItemDecoration.VERTICAL))
    }
}