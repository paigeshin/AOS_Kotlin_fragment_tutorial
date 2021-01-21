package com.paigesoftware.fragmentdatasharing

import android.net.Uri
import androidx.lifecycle.ViewModel

//viewModel은 activity가 죽을 때 사라짐.
class VideoUriViewModel: ViewModel() {

    var videoUri: Uri? = null

}