package uz.fozilbekimomov.mystiker_manager.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import uz.fozilbekimomov.mystiker_manager.core.models.UserDataCount
import uz.fozilbekimomov.mystiker_manager.databinding.ItemUsersBinding


/**
 * Created by <a href="mailto: fozilbekimomov@gmail.com" >Fozilbek Imomov</a>
 *
 * @author fozilbekimomov
 * @version 1.0
 * @date 12/10/20
 * @project MyStiker_Manager
 */


class UsersAdapter(var onItemClickListener: ItemClickListener) :RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    inner class ViewHolder(var binding:  ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(name: String) {
            binding.userName.text = name

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(name)
            }

        }

        fun bindData(name: UserDataCount) {
            binding.userName.text = name.userName
            binding.userCount.text = name.dataCount.toString()

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(name)
            }
        }

    }


    private var users = ArrayList<String>()
    private var usersData = ArrayList<UserDataCount>()

    fun setData(users:HashSet<String>){

        this.users= ArrayList(users)
        notifyDataSetChanged()

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding=ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.bindData(users[position])

    override fun getItemCount(): Int=users.size

    fun setData(users:ArrayList<UserDataCount>) {
        this.usersData= users
        notifyDataSetChanged()
    }


}