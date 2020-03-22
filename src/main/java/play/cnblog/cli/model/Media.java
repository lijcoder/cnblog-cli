package play.cnblog.cli.model;

/**
 * @author LiJie
 */
public class Media {
    /**
     * 文件名称，要带上扩展名
     * eg： a.jpg
     */
    private String name;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件的byte数组
     */
    private byte[] bits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getBits() {
        return bits;
    }

    public void setBits(byte[] bits) {
        this.bits = bits;
    }
}
