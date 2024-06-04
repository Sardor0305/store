package uz.pdp.store.unitls;

import lombok.experimental.UtilityClass;
import org.antlr.v4.runtime.misc.Utils;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;




@UtilityClass
public class CommonUtils extends Utils {

    public static String generateUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String likeFormat(String value) {
        return MessageFormat.format("%{0}%", value);
    }

    public static LocalDateTime currentDateTime() {
        return LocalDateTime.now();
    }

    public static String toLikePattern(String name) {
        return "%" + name + "%";
    }
    public static Date fromLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
