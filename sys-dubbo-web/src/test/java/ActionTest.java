import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class ActionTest {

    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(context);
    }

    @Test
    public void testActionScope() throws Exception {

        System.out.print(context.getBean("indexAction") != context.getBean("indexAction"));
    }

}