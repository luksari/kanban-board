import com.mvvm.kanban_board.data.Repo.Repository
import com.mvvm.kanban_board.data.Repo.RepositoryImpl
import com.mvvm.kanban_board.data.db.KanbanDatabase
import com.mvvm.kanban_board.view.Board.BoardViewModel
import com.mvvm.kanban_board.view.CardDetails.CardDetailsViewModel
import com.mvvm.kanban_board.view.CreateBoard.CreateBoardViewModel
import com.mvvm.kanban_board.view.DrawerMenu.BoardHistory.BoardHistoryViewModel
import com.mvvm.kanban_board.view.DrawerMenu.DrawerMenuViewModel
import com.mvvm.kanban_board.view.DrawerMenu.Members.MembersViewModel
import com.mvvm.kanban_board.view.DrawerMenu.Settings.SettingsViewModel
import com.mvvm.kanban_board.view.DrawerMenu.YourBoards.YourBoardsViewModel
import com.mvvm.kanban_board.view.EnterBoard.EnterBoardViewModel
import com.mvvm.kanban_board.view.MainActivityViewModel
import com.mvvm.kanban_board.view.SignIn.SignInViewModel
import com.mvvm.kanban_board.view.SignUp.SignUpViewModel
import com.mvvm.kanban_board.view.TopBar.ActionBar.ActionBarViewModel
import com.mvvm.kanban_board.view.TopBar.PagesBar.PagesBarViewModel
import com.mvvm.kanban_board.view.TopBar.TopBarViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { RepositoryImpl(get()) as Repository }
    single { KanbanDatabase.buildDatabase(androidApplication())}
    single { get<KanbanDatabase>().kanbanDao() }

    // All injected viewModels  will be probably with following syntax:
    // viewModel { MainActivityViewModel(get()) } : Where get() is repository that we will have to inject.
    // We will have to inject API also so it may be { MainActivityViewModel(get(), get()) } if we register Retrofit API operator in some way

    viewModel { MainActivityViewModel(get()) }
    viewModel { BoardViewModel(get()) }
    viewModel { CardDetailsViewModel(get()) }
    viewModel { DrawerMenuViewModel(get()) }
    viewModel { BoardHistoryViewModel(get()) }
    viewModel { YourBoardsViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { MembersViewModel(get()) }
    viewModel { EnterBoardViewModel(get()) }
    viewModel { CreateBoardViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ActionBarViewModel(get()) }
    viewModel { PagesBarViewModel(get()) }
    viewModel { TopBarViewModel(get()) }



}
