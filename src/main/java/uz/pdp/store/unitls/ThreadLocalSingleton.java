package uz.pdp.store.unitls;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.pdp.store.enitity.User;




@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalSingleton {

    @Getter
    private static final ThreadLocalSingleton INSTANCE = new ThreadLocalSingleton();

    private static final ThreadLocal<User> USER = ThreadLocal.withInitial(User::new);

    public static <T> void remove(ThreadLocal<T> threadLocal) {
        threadLocal.remove();
    }

    public static void removeAll() {
        remove(USER);
    }

    public static User getUser() {
        return USER.get();
    }

    public static void setUser(User user) {
        ThreadLocalSingleton.USER.set(user);
    }

}
