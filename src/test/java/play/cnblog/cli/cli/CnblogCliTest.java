package play.cnblog.cli.cli;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.cnblog.cli.Application;
import play.cnblog.cli.model.Blog;

/**
 * @author LiJie
 */
public class CnblogCliTest {

    @Before
    public void init() throws IOException {
        Application.main(null);
    }

    @Test
    public void add() {
        String filePath = "D:\\blog\\note\\数据结构与算法\\算法及算法分析.md";
        CnblogCliImpl cnblogCli = new CnblogCliImpl();
        String blogId = cnblogCli.add(filePath);
        Assert.assertNotNull(blogId);
        String orgId = cnblogCli.getId(filePath);
        Assert.assertEquals(blogId, orgId);
    }

    @Test
    public void update() {
        String filePath = "D:\\blog\\note\\数据结构与算法\\算法及算法分析.md";
        CnblogCliImpl cnblogCli = new CnblogCliImpl();
        boolean flag = cnblogCli.update(filePath);
        Assert.assertTrue(flag);
    }

    @Test
    public void get() {
        String filePath = "D:\\blog\\note\\数据结构与算法\\算法及算法分析.md";
        CnblogCliImpl cnblogCli = new CnblogCliImpl();
        Blog blog = cnblogCli.get(filePath);
        Assert.assertNotNull(blog);
    }
}
