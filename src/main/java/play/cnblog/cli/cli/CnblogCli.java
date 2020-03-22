package play.cnblog.cli.cli;

import play.cnblog.cli.model.Blog;

/**
 * @author LiJie
 */
public interface CnblogCli {

    /**
     * 新增博文，返回的 ID 会跟新到文件的第一行第一列
     *
     * @param filePath 博文绝对路径
     * @return 该博文的唯一 ID
     */
    String add(String filePath);

    /**
     * 修改博文，第一行第一列的ID必须存在
     *
     * @param filePath 博文绝对路径
     * @return true 修改成功
     */
    boolean update(String filePath);

    /**
     * 删除博文，第一行第一列的ID必须存在，按ID删除
     *
     * @param filePath 博文绝对路径
     * @return true 删除成功
     */
    boolean delete(String filePath);

    /**
     * 上传媒体文件
     *
     * @param filePath 文件绝对路径
     * @return 文件 url
     */
    String uploadMedia(String filePath);

    /**
     * 获取远程博文信息
     *
     * @param filePath 本地博文文件
     * @return 远程博文信息
     */
    Blog get(String filePath);
}

