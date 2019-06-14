package com.mvvm.kanban_board.data.apiService

import android.util.Log
import com.mvvm.kanban_board.data.apiService.response.TokenResponse
import com.mvvm.kanban_board.session.SessionManager
import kotlinx.coroutines.*
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import kotlin.coroutines.CoroutineContext

//class TokenAuthenticator: Authenticator{
//    override fun authenticate(route: Route?, response: Response): Request? {
//
//
//
//    }
//
//
//}