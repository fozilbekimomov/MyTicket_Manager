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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import uz.fozilbekimomov.mystiker_manager.core.adapters.ItemClickListener
import uz.fozilbekimomov.mystiker_manager.core.adapters.UserDataAdapter
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import uz.fozilbekimomov.mystiker_manager.core.utils.TAG
import uz.fozilbekimomov.mystiker_manager.databinding.FragmentUserBinding
import uz.fozilbekimomov.mystiker_manager.ui.home.HomeFragmentDirections


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UserFragment : Fragment(), ItemClickListener {

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
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = args.userName
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.userList.adapter = adapter
        binding.userList.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
//
        setObservers()

        userViewModel.loadData(username)

    }

    private fun setObservers() {

        userViewModel.dataList.observe(viewLifecycleOwner, {

            Log.d(TAG, "setObservers: $it")
            adapter.setData(it)

        })

    }

    override fun onItemClick(data: Any) {

        val d=data as UserDataJ

        val action = UserFragmentDirections.actionUserFragmentToMapFragment(d.location)
        findNavController().navigate(action)

    }
}