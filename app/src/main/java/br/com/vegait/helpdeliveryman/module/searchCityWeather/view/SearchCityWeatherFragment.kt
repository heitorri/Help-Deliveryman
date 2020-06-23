package br.com.vegait.helpdeliveryman.module.searchCityWeather.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import br.com.vegait.helpdeliveryman.R
import br.com.vegait.helpdeliveryman.databinding.FragmentSearchCityBinding
import br.com.vegait.helpdeliveryman.module.base.BaseFragment
import br.com.vegait.helpdeliveryman.module.searchCityWeather.viewmodel.SearchCityWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCityWeatherFragment : BaseFragment<FragmentSearchCityBinding>() {

    override fun getLayoutId() = R.layout.fragment_search_city

    override val viewModel: SearchCityWeatherViewModel by viewModel()

    override val progressBar: ProgressBar? by lazy { getViewDataBinding().progressBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.subscribeLiveData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().click()
    }

    private fun subscribeLiveData() {
        viewModel.viewStateLiveData.observe(this, Observer { viewState ->
            when (viewState) {
                is SearchCityWeatherViewModel.ViewState.ShowError -> {
                    getViewDataBinding().showError(getString(R.string.error_field_empty))
                }
                is SearchCityWeatherViewModel.ViewState.ClearError -> {
                    getViewDataBinding().showError(null)
                }
                is SearchCityWeatherViewModel.ViewState.GoToDetail -> {
                    navigate(
                        SearchCityWeatherFragmentDirections.actionSearchByCityToDetailWeatherCity(
                            viewState.weatherDetail
                        )
                    )
                }
            }
        })
    }

    private fun FragmentSearchCityBinding.showError(error: String?) {
        textInputLayout.error = error
    }

    private fun FragmentSearchCityBinding.click() {
        buttonSendInformation.setOnClickListener {
            viewModel.loadWeatherBycity(searchCityNameEditText.text.toString())
        }
    }
}