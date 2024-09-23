package rees46.demo_android.feature.home.presentation.view

import androidx.fragment.app.Fragment
import com.personalization.SDK
import org.koin.android.ext.android.inject
import rees46.demo_android.databinding.FragmentNewFeaturesBinding

class NewFeaturesFragment : Fragment() {
    private lateinit var binding: FragmentNewFeaturesBinding

    private val sdk: SDK by inject()

}
