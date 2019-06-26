import com.mvvm.kanban_board.data.apiService.ApiUtils
import com.mvvm.kanban_board.data.repo.Repository
import com.mvvm.kanban_board.data.repo.RepositoryImpl
import com.mvvm.kanban_board.data.db.KanbanDatabase
import com.mvvm.kanban_board.data.networkDataSource.*
import com.mvvm.kanban_board.view.board_fragment.BoardViewModel
import com.mvvm.kanban_board.view.card_details.CardDetailsViewModel
import com.mvvm.kanban_board.view.create_board.CreateBoardViewModel
import com.mvvm.kanban_board.view.enter_board.EnterBoardViewModel
import com.mvvm.kanban_board.view.MainActivityViewModel
import com.mvvm.kanban_board.view.sign_in.SignInViewModel
import com.mvvm.kanban_board.view.sign_up.SignUpViewModel
import com.mvvm.kanban_board.view.top_bar.TopBarWithPagesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {ApiUtils}
    single { UserNetworkDataSourceImpl (get()) as UserNetworkDataSource}
    single { BoardNetworkDataSourceImpl (get(), get()) as BoardNetworkDataSource}

    single { TaskNetworkDataSourceImpl (get()) as TaskNetworkDataSource}
    single { PageNetworkDataSourceImpl (get()) as PageNetworkDataSource}

    single { RepositoryImpl(get(), get(), get(), get(), get()) as Repository }
    single { KanbanDatabase.buildDatabase(androidApplication())}
    single { get<KanbanDatabase>().kanbanDao() }

    // All injected viewModels  will be probably with following syntax:
    // viewModel { MainActivityViewModel(get()) } : Where get() is repository that we will have to inject.
    // We will have to inject API also so it may be { MainActivityViewModel(get(), get()) } if we register Retrofit API operator in some way

    viewModel { MainActivityViewModel(get()) }
    viewModel { BoardViewModel(get()) }
    viewModel { CardDetailsViewModel(get()) }
    viewModel { EnterBoardViewModel(get()) }
    viewModel { CreateBoardViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { TopBarWithPagesViewModel(get()) }



}
