package com.example.mobilevynils.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobilevynils.R
import com.example.mobilevynils.ui.adapter.AlbumsAdapter
import com.example.mobilevynils.databinding.FragmentAlbumBinding
import com.example.mobilevynils.viewModels.AlbumViewModel


class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var viewModelAdapter: AlbumsAdapter?= null
    private lateinit var viewModel: AlbumViewModel
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumsAdapter()
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_albums)
        mSwipeRefreshLayout?.setOnRefreshListener {
            viewModel.forceRefreshDataFromNetwork()
            val handler = Handler()
            handler.postDelayed({
                if (mSwipeRefreshLayout!!.isRefreshing) {
                    mSwipeRefreshLayout?.isRefreshing = false
                }
            }, 1000)
        }
        /*view.agregarAlbumButt.setOnClickListener {
            val navCreateAlbum = AlbumsFragmentDirections.actionNavigationAlbumsToCreateAlbumFragment()
            view.findNavController().navigate(navCreateAlbum)
        }*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumsRv
        recyclerView.layoutManager = GridLayoutManager(view.context,2)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        var forceRefresh = false
        /*try {
            val args: AlbumFragmentArgs by navArgs()
            if (args.forceRefresh)
            {
                forceRefresh = true
            }
        }
        catch (e:Exception)
        {
            forceRefresh = false
        }*/
        activity.actionBar?.title = getString(R.string.title_albumes)
        viewModel = ViewModelProvider(this, AlbumViewModel.Factory(activity.application, true)).get(AlbumViewModel::class.java)
        viewModel.albums.observe(viewLifecycleOwner, {
            it.apply {
                viewModelAdapter!!.albums = this
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}