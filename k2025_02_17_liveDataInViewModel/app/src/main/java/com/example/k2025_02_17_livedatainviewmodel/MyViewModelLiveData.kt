import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class MyViewModelLiveData(private val savedStateHandle: SavedStateHandle ): ViewModel() {

    private val _myCount = MutableLiveData<Int>(0)
    val myCount: LiveData<Int> = _myCount

    fun incrementMyCount() {
        _myCount.value = _myCount.value?.plus(1)
    }

    fun setMyCount(newValue: Int) {
        _myCount.value = newValue
    }


}
