package com.myapplication.views.albumList

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.myapplication.R
import com.myapplication.databinding.FragmentAlbumBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumListFragment : Fragment() {
    private val viewModel: AlbumListViewModel by viewModels()

    @Inject
    lateinit var adapter: AlbumListAdapter
    private var  selectedFavoriteMenu : Boolean = false
    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_album, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.clickListener.onItemClick = {
            findNavController().navigate(
                AlbumListFragmentDirections.actionAlbumsListToAlbumDetails(it.id)
            )
        }

        adapter.clickListener.onFavoriteItemClick = {
            if(!selectedFavoriteMenu) {
                viewModel.insertAlbumForFavorite(it)
                adapter.notifyDataSetChanged()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getAllData() {
        viewModel.data.observe(viewLifecycleOwner) {
            if(!selectedFavoriteMenu) {
                adapter.submitList(it)
            }
        }
    }

    private fun getAllFavoriteData() {
        viewModel.dataFavorite.observe(viewLifecycleOwner) {
            if(selectedFavoriteMenu) {
                adapter.submitList(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_all -> {
                selectedFavoriteMenu=false
                getAllData()
            }
            R.id.action_favorite -> {
                selectedFavoriteMenu=true
                getAllFavoriteData()
            }
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}