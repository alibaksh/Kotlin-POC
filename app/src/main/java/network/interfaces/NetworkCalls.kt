package network.interfaces

import constants.Constants
import io.reactivex.Observable
import model.Event
import retrofit2.http.GET

/**
 * Created on 4/4/19.
 */
interface NetworkCalls {

    @GET(Constants.GET_POSTS)
    fun getPosts(): Observable<List<Event>>
}