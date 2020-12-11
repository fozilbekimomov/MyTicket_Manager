package uz.fozilbekimomov.mystiker_manager.ui.userData

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import uz.fozilbekimomov.mystiker_manager.R
import uz.fozilbekimomov.mystiker_manager.core.adapters.ItemClickListener
import uz.fozilbekimomov.mystiker_manager.core.adapters.UserDataAdapter
import uz.fozilbekimomov.mystiker_manager.core.models.LocationModelJ
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import uz.fozilbekimomov.mystiker_manager.core.utils.TAG
import uz.fozilbekimomov.mystiker_manager.databinding.FragmentUserBinding


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UserFragment : Fragment(), ItemClickListener {

    private lateinit var mapboxMap: MapboxMap
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    val args: UserFragmentArgs by navArgs()
    private val adapter = UserDataAdapter(this)

    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = args.userName
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.userList.adapter = adapter
        binding.userList.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//
        setObservers()

        binding.workername.text = username

        userViewModel.loadData(username)

        binding.mapView.getMapAsync { mapboxMap ->

            this.mapboxMap = mapboxMap

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {


            }

        }

    }

    private fun setObservers() {

        userViewModel.dataList.observe(viewLifecycleOwner, {

            Log.d(TAG, "setObservers: $it")
            adapter.setData(it)

            binding.dataCount.text = "Общий: ${it.size}"

            for (user in it) {
                addMarker(user.location)
            }

            val user = it.last()

            val position = CameraPosition.Builder()
                .target(LatLng(user.location.latitude, user.location.longitude))
                .zoom(10.0)
                .tilt(20.0)
                .build()

            binding.mapView.getMapAsync { mapboxMap ->
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 3000)
            }
        })

    }


    private fun addMarker(location: LocationModelJ) {
        val position = CameraPosition.Builder()
            .target(LatLng(location.latitude, location.longitude))
            .zoom(15.0)
            .tilt(20.0)
            .build()

        binding.mapView.getMapAsync { mapboxMap ->

//            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 3000)

            mapboxMap.addMarker(
                MarkerOptions().position(LatLng(location.latitude, location.longitude))
            )

        }

    }

    override fun onItemClick(data: Any) {

        val d = data as UserDataJ

        val action = UserFragmentDirections.actionUserFragmentToMapFragment(d.location)
        findNavController().navigate(action)

    }


    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
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