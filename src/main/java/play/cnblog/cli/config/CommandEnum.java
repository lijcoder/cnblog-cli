package play.cnblog.cli.config;

/**
 * @author LiJie
 */
public enum CommandEnum {
    /* ************************ */
    ADD("add") {
        @Override
        public Object exec(String blogFile) {
            return GlobalConfig.cli().add(blogFile);
        }
    },
    UPDATE("update") {
        @Override
        public Object exec(String blogFile) {
            return GlobalConfig.cli().update(blogFile);
        }
    },
    DELETE("delete") {
        @Override
        public Object exec(String blogFile) {
            return GlobalConfig.cli().delete(blogFile);
        }
    },
    FIND("find") {
        @Override
        public Object exec(String blogFile) {
            return GlobalConfig.cli().get(blogFile);
        }
    },
    ;

    private String command;

    public String getCommand() {
        return command;
    }

    CommandEnum(String command) {
        this.command = command;
    }

    public abstract Object exec(String blogFile);

    public static CommandEnum getCommand(String command) {
        return CommandEnum.valueOf(command.toUpperCase());
    }
}
