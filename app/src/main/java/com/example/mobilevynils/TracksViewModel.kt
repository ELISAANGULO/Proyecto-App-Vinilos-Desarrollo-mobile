package com.example.mobilevynils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TracksViewModel : ViewModel()
{
    var name = MutableLiveData<String>()
    var duration = MutableLiveData<String>()
}