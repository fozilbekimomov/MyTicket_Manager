package uz.fozilbekimomov.mystiker_manager.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import uz.fozilbekimomov.mystiker_manager.R
import uz.fozilbekimomov.mystiker_manager.databinding.FragmentMapBinding


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class MapFragment : Fragment() {

    private lateinit var mapboxMap: MapboxMap
    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    val args: MapFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.getMapAsync { mapboxMap ->

            this.mapboxMap = mapboxMap

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {


            }

        }
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()

        val position = CameraPosition.Builder()
            .target(LatLng(args.location.latitude, args.location.longitude))
            .zoom(15.0)
            .tilt(20.0)
            .build()

        binding.mapView.getMapAsync { mapboxMap ->

            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 3000)

            mapboxMap.addMarker(
                MarkerOptions().position(LatLng(args.location.latitude, args.location.longitude))
            )

        }

    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }


    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

}