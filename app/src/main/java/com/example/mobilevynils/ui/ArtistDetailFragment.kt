package com.example.mobilevynils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.navArgs
import com.example.mobilesvynilis.models.Artista
import com.example.mobilevynils.R
import com.example.mobilevynils.databinding.FragmentArtistDetailBinding
import com.example.mobilevynils.ui.adapter.ArtistAdapter
import com.example.mobilevynils.viewModels.ArtistViewModel
import com.example.tsdc_vinilos_equipo6.ui.adapters.ArtistAlbumsAdapter
import com.example.tsdc_vinilos_equipo6.ui.adapters.ArtistPrizesAdapter


class ArtistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var almbumsRecyclerView: RecyclerView
    private lateinit var prizesRecyclerView: RecyclerView
    private lateinit var artist: Artista
    private lateinit var viewModel: ArtistViewModel
    private var viewModelAdapter: ArtistAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistDetailRv

        almbumsRecyclerView = binding.artistAlbumsRv
        almbumsRecyclerView.layoutManager = LinearLayoutManager(context)

        prizesRecyclerView = binding.artistPrizesRv
        prizesRecyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: ArtistDetailFragmentArgs by navArgs()
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_artistas)

        viewModel = ViewModelProvider(this, ArtistViewModel.Factory(activity.application, args.artistId))[ArtistViewModel::class.java]
        viewModel.artist.observe(viewLifecycleOwner) {
            artist = it
            viewModelAdapter?.artist = it
            almbumsRecyclerView.adapter = ArtistAlbumsAdapter(it.albums!!)
            prizesRecyclerView.adapter = ArtistPrizesAdapter(it.prizes!!)
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