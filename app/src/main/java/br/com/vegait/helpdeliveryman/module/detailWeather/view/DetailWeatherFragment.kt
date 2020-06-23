package br.com.vegait.helpdeliveryman.module.detailWeather.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import br.com.vegait.helpdeliveryman.BuildConfig
import br.com.vegait.helpdeliveryman.R
import br.com.vegait.helpdeliveryman.data.model.WeatherDetail
import br.com.vegait.helpdeliveryman.databinding.FragmentDetailCityWeatherBinding
import br.com.vegait.helpdeliveryman.module.base.BaseFragment
import com.bumptech.glide.Glide

class DetailWeatherFragment : BaseFragment<FragmentDetailCityWeatherBinding>() {

    private val args: DetailWeatherFragmentArgs by navArgs()

    override fun getLayoutId() = R.layout.fragment_detail_city_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewDataBinding().setupScreen(args.weatherDetail)
    }

    private fun FragmentDetailCityWeatherBinding.setupScreen(weatherDetail: WeatherDetail) {
        descriptionForecastTextView.text = weatherDetail.description
        subtitle.text = getString(
            R.string.sub_title_detail,
            weatherDetail.dateSearch
        )

        minMaxtempTextView.text = getString(
            R.string.min_max_label,
            weatherDetail.minTemp,
            weatherDetail.maxTemp
        )
        val linkImage = getString(R.string.load_image, BuildConfig.BASE_URL_IMG, weatherDetail.icon)
        Glide.with(this@DetailWeatherFragment)
            .load(linkImage)
            .into(forecastImage)
    }
}