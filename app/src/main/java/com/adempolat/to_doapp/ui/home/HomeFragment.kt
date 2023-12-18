package com.adempolat.to_doapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adempolat.to_doapp.R
import com.adempolat.to_doapp.databinding.FragmentHomeBinding
import com.adempolat.to_doapp.model.ToDoModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),ToDoClickListener, SearchView.OnQueryTextListener {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner=viewLifecycleOwner
        binding.viewmodel=viewModel
        binding.toDoClickListener=this

        setHasOptionsMenu(true)
        // Inflate the layout for this fragment

        binding.fragmentHomeFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newandEditFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled= true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onToDoClick(id: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewandEditFragment(id)
        findNavController().navigate(action)
    }

    override fun onToDoChecked(toDoModel: ToDoModel) {
        viewModel.updateToDo(toDoModel)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.searchTodo(it)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            viewModel.searchTodo(it)
        }
        return true
    }

}