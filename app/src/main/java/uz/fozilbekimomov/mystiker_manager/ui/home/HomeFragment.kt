package uz.fozilbekimomov.mystiker_manager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.fozilbekimomov.mystiker_manager.core.adapters.ItemClickListener
import uz.fozilbekimomov.mystiker_manager.core.adapters.UsersAdapter
import uz.fozilbekimomov.mystiker_manager.databinding.FragmentHomeBinding


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class HomeFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = UsersAdapter(this)

    private lateinit var homeViewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeList.adapter = adapter
        binding.homeList.layoutManager = LinearLayoutManager(requireContext())

        setObservers()

        homeViewModel.loadData()

    }

    private fun setObservers() {
        homeViewModel.homeUserList.observe(viewLifecycleOwner, {

        })

        homeViewModel.stateLoading.observe(viewLifecycleOwner, {
            binding.progressHome.visibility = if (it) View.VISIBLE else View.GONE
        })

        homeViewModel.usersName.observe(viewLifecycleOwner, {
            adapter.setData(it)
            if (it.size > 0) {
                binding.homeText.visibility=View.GONE
            }else{
                binding.homeText.visibility=View.VISIBLE
            }
        })
    }

    override fun onItemClick(data: Any) {
        val action = HomeFragmentDirections.actionHomeFragmentToUserFragment(data as String)
        findNavController().navigate(action)
    }
}