import com.atguigu.commonutils.Result;
import com.guli.oss.OssApplication;
import com.guli.oss.client.EduServiceClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = OssApplication.class)
@RunWith(SpringRunner.class)
public class OssAllicationTest {
    @Autowired
    private EduServiceClient eduServiceClient;
    @Test
    public void test(){
        Result result = eduServiceClient.searchCourseById("14");
        System.out.println(result);
    }
}
