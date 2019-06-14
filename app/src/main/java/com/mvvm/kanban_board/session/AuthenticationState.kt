package com.mvvm.kanban_board.session


enum class AuthenticationState {
    UNAUTHENTICATED,          // Initial state, the user needs to authenticate
    AUTHENTICATED,        // The user has authenticated successfully
}