package posts

import android.arch.lifecycle.MutableLiveData
import android.view.View
import base.BaseViewModel
import com.flutterpakistan.app.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import model.Event
import network.interfaces.NetworkCalls
import javax.inject.Inject

/**
 * Created on 4/4/19.
 */
class EventListViewModel : BaseViewModel() {

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val postListAdapter: PostListAdapter = PostListAdapter()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private lateinit var subscription: Disposable

    @Inject
    lateinit var postApi: NetworkCalls

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart() {

        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {

        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(eventList: List<Event>) {
        postListAdapter.updatePostList(eventList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun bind(event: Event) {
        postTitle.value = event.title
        postBody.value = event.body
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }
}