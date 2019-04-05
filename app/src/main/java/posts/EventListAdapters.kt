package posts

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.flutterpakistan.app.R
import com.flutterpakistan.app.databinding.ItemPostBinding
import model.Event

/**
 * Created on 4/4/19.
 */
class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private lateinit var eventList: List<Event>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListAdapter.ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListAdapter.ViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return if (::eventList.isInitialized) eventList.size else 0
    }

    fun updatePostList(eventList: List<Event>) {
        this.eventList = eventList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = EventListViewModel()

        fun bind(event: Event) {
            viewModel.bind(event)
            binding.viewModel = viewModel
        }
    }
}