package console;

import fileWork.DatabaseProvider;
import subjects.Ticket;

public class TicketsDAO {
    /*здесь будут методы взаимодействия с бд
    то есть
    в каталоге у меня мои обычные методы
    но для некоторых потом еще при успешном выполнении
    применен соответствующий метод отсюда

    по типу : удаление обьекта
    в каталоге все как обычно, но код дополнен методом удалить из бд
     */
    private final DatabaseProvider provider;
    public TicketsDAO(OnlineUser user){
        this.provider = new DatabaseProvider(user);
    }
    public void removeElementFromBase(Ticket ticket){




    }
    public


}
