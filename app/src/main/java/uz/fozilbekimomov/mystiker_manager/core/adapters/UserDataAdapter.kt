package uz.fozilbekimomov.mystiker_manager.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataJ
import uz.fozilbekimomov.mystiker_manager.databinding.ItemUserDataBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UserDataAdapter(var onItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {


    private var storageRef = FirebaseStorage.getInstance().reference

    lateinit var storage: FirebaseStorage

    inner class ViewHolder(var binding: ItemUserDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(userDataJ: UserDataJ) {

            binding.userName.text ="${ userDataJ.userName}"
            binding.userNumber.text = "${userDataJ.phoneNumber}"
            binding.userAdded.text =
                SimpleDateFormat("dd-MMM yyyy HH:mm",Locale("ru-RU")).format(Date(userDataJ.addedTime))

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(userDataJ)
            }


            val spaceRef = storageRef.child(userDataJ.imageUrl)
            spaceRef.downloadUrl.addOnCompleteListener {
                if (it.isSuccessful) {
                    Glide.with(binding.imageSticker).load(it.result).into(binding.imageSticker)
                }
            }
        }

    }


    private var users = ArrayList<UserDataJ>()

    fun setData(users: List<UserDataJ>) {

        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemUserDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(users[position])

    override fun getItemCount(): Int = users.size


}