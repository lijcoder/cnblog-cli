package play.cnblog.cli.rpc;

import play.cnblog.cli.model.Blog;
import play.cnblog.cli.model.Media;
import play.cnblog.cli.model.User;

/**
 * 博客园的操作API
 *
 * @author LiJie
 */
public interface CnblogRpc {
    /**
     * 创建博文
     *
     * @return 博文 ID
     */
    String createBlog(Blog blog, User user);

    /**
     * 修改博文
     *
     * @return true 修改成功
     */
    boolean updateBlog(String blogId, Blog blog, User user);

    /**
     * 获取博文
     */
    Blog getBlog(String blogId, User user);

    /**
     * 删除博文
     *
     * @return true 删除成功
     */
    boolean deleteBlog(String blogId, User user);

    /**
     * 上传素材
     *
     * @return url 生成的素材链接
     */
    String uploadMedia(Media media, User user);
}
