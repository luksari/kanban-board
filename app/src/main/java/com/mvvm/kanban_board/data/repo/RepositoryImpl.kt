package com.mvvm.kanban_board.data.repo


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.kanban_board.data.apiService.response.BoardResponse
import com.mvvm.kanban_board.data.apiService.response.PageResponse
import com.mvvm.kanban_board.data.apiService.response.TaskResponse
import com.mvvm.kanban_board.data.apiService.response.UserRegisterResponse
import com.mvvm.kanban_board.data.db.KanbanDao
import com.mvvm.kanban_board.data.networkDataSource.*
import com.mvvm.kanban_board.session.AuthenticationState
import com.mvvm.kanban_board.session.SessionManager



class RepositoryImpl(
    private val kanbanDao: KanbanDao,
    private val userNetworkDataSource: UserNetworkDataSource,
    private val boardNetworkDataSource: BoardNetworkDataSource,
    private val pageNetworkDataSource: PageNetworkDataSource,
    private val taskNetworkDataSource: TaskNetworkDataSource
  ) : Repository {

    private val _authenticationState = MutableLiveData<AuthenticationState>()
    override val authenticationState: LiveData<AuthenticationState>
        get() = _authenticationState


    private val _currentBoard = MutableLiveData<BoardResponse>()
    override val currentBoard: LiveData<BoardResponse>
        get() = _currentBoard


    private val _currentTask = MutableLiveData<TaskResponse>()
    override val currentTask: LiveData<TaskResponse>
        get() = _currentTask

    private val _currentBoardPages = MutableLiveData<List<PageResponse>>()
    override val currentBoardPages: LiveData<List<PageResponse>>
        get() = _currentBoardPages

    //it is not always actual current page as we see, because tabs are loading in very strange queue, before changed them loading invisible tabs
    private val _currentPage = MutableLiveData<PageResponse>()
    override val currentPage: LiveData<PageResponse>
        get() = _currentPage

    init{
         boardNetworkDataSource.currentBoard.observeForever { board ->
             _currentBoard.value = board
         } //-> here change in room
}

    override suspend fun registerNewUser(name: String, password: String): String {
        return userNetworkDataSource.registerUser(name, password)
    }

    override suspend fun loginUser(name: String, password: String): String? {
        return userNetworkDataSource.loginUser(name, password)
    }

    override suspend fun createBoard(identifier: String, name: String): String? {
        return boardNetworkDataSource.addBoard(identifier, name, SessionManager.userID!!.toLong())
    }


    override suspend fun enterBoard(identifier: String): String? {
        //after success _currentBoard.value is changed by obvserver
        return boardNetworkDataSource.enterBoard(identifier)
    }

    override suspend fun loadBoardPages(){
       _currentBoardPages.value = pageNetworkDataSource.loadBoardPages(_currentBoard.value!!.id) //16
    }
    override suspend fun setCurrentTask(taskID: Long){
        _currentTask.value = taskNetworkDataSource.loadTask(taskID)
    }

    override suspend fun loadPageTasks(pageName: String): List<TaskResponse>?{
        loadBoardPages()
        _currentPage.value = _currentBoardPages.value?.first { p -> p.name == pageName }
        return taskNetworkDataSource.loadPageTasks(_currentPage.value?.id)
    }

    override suspend fun addTaskToPage(pageName: String): String? { //TASK PAGE ID NEED
        loadBoardPages()
        val pageID = _currentBoardPages.value?.first { p -> p.name == pageName }?.id
        _currentTask.value = taskNetworkDataSource.addTaskToPage("", SessionManager.userID!!.toLong(), "", pageID!!)
        return ""
    }

    //FIRSTLY CHECK IF THE TASK EXIST/IS NOT CHANGED

    override suspend fun deleteSelectedTask(): String? {
        return taskNetworkDataSource.deleteTasks(_currentTask.value!!.id)
    }


    override suspend fun editTask(editedTask: TaskResponse): String? {
        loadBoardPages()
        return taskNetworkDataSource.editTasks(editedTask)
        return ""
    }
    override suspend fun loadUser(userID: Long?): UserRegisterResponse? {
        userID?.let{
            return  userNetworkDataSource.loadUser(userID)
        }
        return null
    }
    override fun logout(){
        _authenticationState.value = AuthenticationState.UNAUTHENTICATED
        SessionManager.accessToken = null
        SessionManager.username = null
        SessionManager.userID = null
    }

    override fun exitBoard() {
        _currentBoard.value = null
    }


    init {
       userNetworkDataSource.authenticationState.observeForever {
           _authenticationState.value = it
       }
       boardNetworkDataSource.currentBoard.observeForever {
           _currentBoard.value = it
       }
   }
}