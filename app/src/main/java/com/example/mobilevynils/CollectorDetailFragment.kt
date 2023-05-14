package com.example.mobilevynils

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilevynils.databinding.AlbumDetailFragmentBinding
import com.example.mobilevynils.databinding.FragmentCollectorDetailBinding
import com.example.mobilevynils.ui.adapter.AlbumAdapter
import com.example.mobilevynils.ui.adapter.AlbumTracksAdapter
import com.example.mobilevynils.ui.adapter.AlbumsAdapter
import com.example.mobilevynils.ui.adapter.CollectorAdapter
import com.example.mobilevynils.ui.adapter.CollectorDetailAlbumsAdapter
import com.example.mobilevynils.ui.fragments.AlbumDetailFragmentArgs
import com.example.mobilevynils.viewModels.AlbumViewModel
import com.example.mobilevynils.viewModels.CollectorViewModel
import com.example.mobilevynils.viewModels.CollectorsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CollectorDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CollectorDetailFragment : Fragment() {
    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorViewModel
    private var viewModelAdapter: CollectorAdapter? = null
    private lateinit var albumsRecyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectorItemRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        albumsRecyclerView = binding.collectorAlbumItemRv
        albumsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: CollectorDetailFragmentArgs by navArgs()
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_collectors)
        Log.i("CollectorId", "${args.collectorId}")
        viewModel = ViewModelProvider(
            this,
           CollectorViewModel.Factory(activity.application, args.collectorId)
        )[CollectorViewModel::class.java]
        viewModel.collector.observe(viewLifecycleOwner) {
            it.apply {
                viewModelAdapter!!.collector = this
                albumsRecyclerView.adapter = CollectorDetailAlbumsAdapter(it.albums!!)
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