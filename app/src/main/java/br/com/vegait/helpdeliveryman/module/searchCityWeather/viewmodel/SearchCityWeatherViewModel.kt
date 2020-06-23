package br.com.vegait.helpdeliveryman.module.searchCityWeather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.vegait.helpdeliveryman.data.model.WeatherDetail
import br.com.vegait.helpdeliveryman.domain.usecase.LoadWeaterByNameUseCase
import br.com.vegait.helpdeliveryman.module.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchCityWeatherViewModel(
    private val usecase: LoadWeaterByNameUseCase
) : BaseViewModel() {

    sealed class ViewState {
        class GoToDetail(val weatherDetail: WeatherDetail) : ViewState()
        object ShowError : ViewState()
        object ClearError : ViewState()
    }

    val viewStateLiveData = MutableLiveData<ViewState>()

    fun loadWeatherBycity(cityName: String) {

        if (cityName.isEmpty()) {
            viewStateLiveData.value = ViewState.ShowError
            return
        }
        showProgressDialog.value = true

        viewStateLiveData.value = ViewState.ClearError

        viewModelScope.launch {
            try {
                val weatherDetail = usecase.loadWeatherBycity(cityName)
                viewStateLiveData.value = ViewState.GoToDetail(weatherDetail)
            } catch (error: Exception) {
                handleError(error)
            } finally {
                showProgressDialog.value = false
            }
        }
    }
}