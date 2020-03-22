package play.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import net.coobird.thumbnailator.Thumbnails;

/**
 * @author LiJie
 */
public class BlogFileTest {

    @Test
    public void readFile() throws IOException {
        String filePath = "D:\\blog\\note\\数据结构与算法\\目录.md";
        List<String> contentList = Files.readAllLines(Paths.get(filePath));
        String firstLine = contentList.get(0);
        String[] info = firstLine.split("\\|");
        info[0] = "123qwe";
        StringBuilder firstUpdate = new StringBuilder(firstLine.length() + 20);
        for (String field : info) {
            firstUpdate.append(field).append("|");
        }
        contentList.set(0, firstUpdate.toString());
        Path path = Files.write(Paths.get(filePath), contentList);
        Assert.assertNotNull(contentList);
    }

    @Test
    public void image() throws IOException {
        String file = "C:\\Users\\admin\\Desktop\\taiji.png";
        String target = "C:\\Users\\admin\\Desktop\\taiji_target.png";
        Thumbnails.of(file).scale(0.3).outputQuality(0.2f).toFile(target);
    }
}
