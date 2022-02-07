package com.technical.mmchallenge.ui.fragment.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.technical.mmchallenge.R
import com.technical.mmchallenge.data.model.Photos
import com.technical.mmchallenge.databinding.FragmentPhotoBinding
import com.technical.mmchallenge.ui.activity.MainActivity
import com.technical.mmchallenge.ui.adapter.PhotosAdapter

class PhotoFragment: Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var photo: Photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            photo = it.getParcelable("photo")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Photo"
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val glideUrl = GlideUrl(
            photo.url,
            LazyHeaders.Builder().addHeader("User-Agent", PhotosAdapter.USER_AGENT).build())

        Glide.with(requireContext())
            .load(glideUrl)
            .into(binding.photo)
        binding.textDetail.text = photo.title

        binding.photoContainer.setOnClickListener {
            if (binding.textDetail.isVisible){
                binding.textDetail.visibility = View.INVISIBLE
            } else {
                binding.textDetail.visibility = View.VISIBLE
            }
        }
    }
}