package play.cnblog.cli.config;

import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import play.cnblog.cli.cli.CnblogCli;
import play.cnblog.cli.cli.CnblogCliImpl;
import play.cnblog.cli.model.User;

/**
 * @author LiJie
 */
public class GlobalConfig {

    private static User USER;
    private static String METAWEBLOG_URL;
    private static CnblogCli cnblogCli;

    public static CnblogCli cli() {
        return cnblogCli;
    }

    public static User getUser() {
        return USER;
    }

    public static String getMetaWeblogUrl() {
        return METAWEBLOG_URL;
    }

    public static void initModel(Properties props) {
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String mateWeblogUrl = props.getProperty("metaWeblog.url");
        if (StringUtils.isBlank(username)
            || StringUtils.isBlank(password)
            || StringUtils.isBlank(mateWeblogUrl)) {

            throw new IllegalArgumentException
                ("username, password, metaWeblog.url not exist");
        }
        METAWEBLOG_URL = mateWeblogUrl;

        USER = new User();
        USER.setUsername(username);
        USER.setPassword(password);

        cnblogCli = new CnblogCliImpl();
    }
}
