package play.xmlrpc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import play.cnblog.cli.model.Blog;
import play.cnblog.cli.model.Media;
import play.cnblog.cli.model.User;
import play.cnblog.cli.rpc.CnblogRpc;
import play.cnblog.cli.rpc.impl.CnblogXmlRpc;

/**
 * @author LiJie
 */
public class CnblogRpcTest {

    private static final CnblogRpc XML_RPC = new CnblogXmlRpc();

    private static final User USER = new User();

    static {
        USER.setUsername("");
        USER.setPassword("");
    }

    @Test
    public void createBlog() {
        Blog blog = new Blog();
        blog.setTitle("java测试");
        blog.setMt_excerpt("这是一个由java开发的对接博客园本地编辑文档的教程");
        blog.setDescription(">java 测试metaWeblog接口对接");
        blog.setCategories(new ArrayList<String>(3) {{
            add("[Markdown]");
            add("[随笔分类]文章翻译");
            add("[随笔分类]数据库技术");
        }});
        blog.setMt_keywords("linux,java,api");
        String blogId = XML_RPC.createBlog(blog, USER);
        System.out.println(blogId);
        Assert.assertNotNull(blogId);
    }

    @Test
    public void updateBlog() {
        String blogId = "12517438";
        Blog blog = new Blog();
        blog.setTitle("java测试修改");
        blog.setMt_excerpt("这是一个由膝盖java开发的对接博客园本地编辑文档的教程");
        blog.setDescription(">>> java 测试metaWeblog接口对接");
        blog.setCategories(new ArrayList<String>(3) {{
            add("[Markdown]");
            add("[随笔分类]文章翻译");
        }});
        blog.setMt_keywords("java,  api");
        boolean flag = XML_RPC.updateBlog(blogId, blog, USER);
        Assert.assertTrue(flag);
    }

    @Test
    public void getBlog() {
        String blogId = "12517438";
        Blog blog = XML_RPC.getBlog(blogId, USER);
        Assert.assertNotNull(blog);
    }

    @Test
    public void deleteBlog() {
        String blogId = "12518075";
        boolean flag = XML_RPC.deleteBlog(blogId, USER);
        Assert.assertTrue(flag);
    }

    @Test
    public void uploadMedia() throws IOException {
        String file = "C:\\Users\\admin\\Desktop\\taiji.jpg";
        byte[] bs = Files.readAllBytes(Paths.get(file));
        Media media = new Media();
        media.setBits(bs);
        media.setName("taiji.jpg");
        media.setType("image/jpg");
        String url = XML_RPC.uploadMedia(media, USER);
        System.out.println(url);
        Assert.assertNotNull(url);
    }
}
