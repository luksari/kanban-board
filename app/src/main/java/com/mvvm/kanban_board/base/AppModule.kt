import com.mvvm.kanban_board.data.Repo.Repository
import com.mvvm.kanban_board.data.Repo.RepositoryImpl
import com.mvvm.kanban_board.data.db.KanbanDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val appModule = module {

    single { RepositoryImpl(get()) as Repository }
    single { KanbanDatabase.buildDatabase(androidApplication())}
    single { get<KanbanDatabase>().kanbanDao() }

}