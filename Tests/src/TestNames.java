import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.apache.commons.lang3.StringUtils;

public class TestNames {

    public static boolean matchesCommand(String[] messages, String[] commands) {
        for (String command : commands) {
            for (String message : messages) {
                if (StringUtils.isNotBlank(message)
                        && message.trim().toLowerCase().indexOf(command.trim().toLowerCase()) != -1) {
                    return true;
                }
                // check for blank message.
                if (StringUtils.isBlank(message) && command.toLowerCase().indexOf("_blank_") != -1) {
                    return true;
                }
            }
            if (command.toLowerCase().indexOf("_unknown_") != -1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Base64DecodingException {
        String[] messages = {"StartMC", "StartMC"};
        String[] commands = {"start", "START", "Y", "y"};

        System.out.println(matchesCommand(messages, commands));
    }

}