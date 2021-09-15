package com.example.cloud.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cloud.R
import com.example.cloud.adapter.homeAdapter.HomeAdapter
import com.example.cloud.databinding.FragmentHomeBinding
import com.example.cloud.viewModels.ViewModelFactory
import com.example.cloud.viewModels.homeViewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        viewModelFactory = ViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val manager = GridLayoutManager(activity,2)
        val adapter = HomeAdapter()
        binding.recyclerView1.layoutManager = manager
        binding.recyclerView1.adapter = adapter
        viewModel.cloudList.observe(viewLifecycleOwner,{
            adapter.data = it
            adapter.notifyDataSetChanged()
        })
        viewModel.showData(binding.userimg)
        return binding.root
    }

}