package com.example.mobilevynils

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilesvynilis.models.Track
import com.example.mobilevynils.databinding.AddTrackFragmentBinding
import com.example.mobilevynils.network.CacheManager
import com.example.mobilevynils.network.NetworkServiceAdapter
import com.example.mobilevynils.ui.adapter.AlbumTracksAdapter
import com.example.mobilevynils.ui.fragments.AlbumDetailFragment
import com.example.mobilevynils.viewModels.AlbumViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import org.json.JSONObject

class TracksDurationFragment (private var  viewModelAvm: AlbumViewModel, private  val albDetFrag: AlbumDetailFragment, val  recView: RecyclerView, val albumId: Int): BottomSheetDialogFragment() {
    private lateinit var binding: AddTrackFragmentBinding
    private lateinit var taskViewModel: TracksViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        taskViewModel = ViewModelProvider(activity).get(TracksViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddTrackFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
    private fun saveAction() {
        taskViewModel.name.value = binding.name.text.toString()
        taskViewModel.duration.value = binding.duration.text.toString()
        val track = Track(trackId = 1, name = binding.name.text.toString() , duration =binding.duration.text.toString())
        var gson = Gson()
        var jsonString = gson.toJson(track)
        var dataAlbum = JSONObject(jsonString);
        dataAlbum.remove("trackId")
        NetworkServiceAdapter.getInstance(binding.root.context).postAsociarCancion(dataAlbum,albumId,
            onComplete = { resp ->
                Log.d("Respuesta exitosa: $resp", "")
                println("Respuesta exitosa: $resp")
                CacheManager.getInstance(binding.root.context).deleteAlbum()

                viewModelAvm.addNewTrack(track)
                var tracks= viewModelAvm.album.value?.tracks

                var newTrask = (viewModelAvm.album.value?.tracks as List<Track>)
                val albumTracksAdapter = AlbumTracksAdapter(newTrask)
                recView.adapter= albumTracksAdapter
                albumTracksAdapter.updateItems(newTrask)
                albumTracksAdapter?.notifyDataSetChanged()

                binding.name.setText("")
                binding.duration.setText("")
            },
            onError = { error ->

                Log.d("Error: $error",  "")

            }

            )
        dismiss()
    }
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

}

class AddTrackFragmentBinding {

}
