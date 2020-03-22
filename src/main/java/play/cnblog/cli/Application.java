package play.cnblog.cli;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;
import play.cnblog.cli.config.CommandEnum;
import play.cnblog.cli.config.GlobalConfig;

/**
 * @author LiJie
 */
public class Application {

    public static void main(String[] args) throws IOException {
        valid();
        String configPath = System.getProperty("config.file");
        if (StringUtils.isBlank(configPath)) {
            throw new FileNotFoundException(configPath + " nor found.");
        }
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(configPath)) {
            props.load(is);
            GlobalConfig.initModel(props);
        }
        String command = System.getProperty("exec");
        String blogFile = System.getProperty("blog");
        CommandEnum commandEnum = CommandEnum.getCommand(command);
        Object result = commandEnum.exec(blogFile);
        System.out.println(JSONObject.toJSONString(result));
    }

    private static void valid() {
        if (StringUtils.isBlank(System.getProperty("config.file"))) {
            throw new IllegalArgumentException("config file not found.");
        }
        if (StringUtils.isBlank(System.getProperty("exec"))) {
            throw new IllegalArgumentException("exec command not found.");
        }
        if (StringUtils.isBlank(System.getProperty("blog"))) {
            throw new IllegalArgumentException("blog file not found.");
        }
    }
}
