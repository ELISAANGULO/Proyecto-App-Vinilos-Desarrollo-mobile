package com.example.mobilevynils.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mobilevynils.TracksDurationFragment
import com.example.mobilevynils.R
import com.example.mobilevynils.TracksViewModel
import com.example.mobilevynils.databinding.AlbumDetailFragmentBinding
import com.example.mobilevynils.ui.adapter.AlbumAdapter
import com.example.mobilevynils.ui.adapter.AlbumTracksAdapter
import com.example.mobilevynils.viewModels.AlbumViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumDetailFragment : Fragment() {
    private var _binding: AlbumDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: AlbumViewModel
    private var viewModelAdapter: AlbumAdapter? = null
    private lateinit var tracksRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayoutTrackDetail: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AlbumDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = AlbumAdapter()
        swipeRefreshLayoutTrackDetail = view.findViewById(R.id.swipeRefreshLayoutTrackAlbumDetail)
        swipeRefreshLayoutTrackDetail.setOnRefreshListener {
            Log.d("refreshAlbums","Refresh")
            viewModel.refreshData(swipeRefreshLayoutTrackDetail)
        }
        return view
    }

    private lateinit var taskViewModel: TracksViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.albumDetailRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        tracksRecyclerView = binding.albumTracksRv
        tracksRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.newTaskButton.setOnClickListener{
            var  fragManager= (view.context as FragmentActivity).supportFragmentManager
            viewModel.album?.value?.albumId?.let { it1 ->
                TracksDurationFragment(viewModel,this, tracksRecyclerView,
                    it1
                ).show(fragManager,"newTaskTag" )
            }



        }
        //loadData()
    }

     fun loadData() {
        val args: AlbumDetailFragmentArgs by navArgs()
        val activity = requireActivity()
        activity.actionBar?.title = getString(R.string.title_albumes)
        Log.i("AlbumId  katami ----", "${args.albumId}")
         println("AlbumId  katami ----${args.albumId}")
        viewModel = ViewModelProvider(
            this,
            AlbumViewModel.Factory(activity.application, args.albumId)
        ).get(AlbumViewModel::class.java)

        viewModel.album.observe(viewLifecycleOwner) {
            it?.apply {
                viewModelAdapter?.album = this

                tracksRecyclerView.adapter = AlbumTracksAdapter(it.tracks!!)

            }
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: AlbumDetailFragmentArgs by navArgs()
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albumes)
        Log.i("AlbumId", "${args.albumId}")
        viewModel = ViewModelProvider(
            this,
            AlbumViewModel.Factory(activity.application, args.albumId)
        )[AlbumViewModel::class.java]
        viewModel.album.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.album = this
                tracksRecyclerView.adapter = AlbumTracksAdapter(it.tracks!!)
            }
        }
        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}