import com.fuckyou.pojo.UserT;
import com.fuckyou.service.UserTService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Created by 陈源熹 on 2017-06-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:/spring/spring-mybatis.xml"})
public class TestMyBatis {
    private static Logger logger = Logger.getLogger(String.valueOf(TestMyBatis.class));
    //	private ApplicationContext ac = null;
    @Resource
    private UserTService userService;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

    @Test
    public void test1() throws UnsupportedEncodingException {
//        UserT user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
//        logger.info(user.getId()+"=+++");


        String str = "我ABC汉 ";
        int num = trimGBK(str.getBytes("GBK"),5);
        System.out.println(str.substring(0,num));
    }

  public static final char[] data = new char[]{'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};
  public  static final char[] units = new char[]{'元','拾','佰','仟','万','拾','佰','仟','亿' };

    @Test
    public void test2()
    {

        System.out.println(convert(135689123));

    }



    public
    static
    String
    convert(int
                    money)
    {
        StringBuffer sbf = new StringBuffer();
        int unit = 0;
        while(money!=0)
        {
            sbf.insert(0,units[unit++]);
            int number = money%10;
            sbf.insert(0,data[number]);
            money /= 10;
        }
        return sbf.toString();
    }



    public
    static
    int
    trimGBK(byte[] buf,int n){
        int num = 0;
        boolean bChineseFirstHalf = false;
        for(int i=0;i<n;i++)

        {

            if(buf[i]<0 && !bChineseFirstHalf){
                bChineseFirstHalf = true;
            }else{
                num++;
                bChineseFirstHalf = false;
            }
        }
        return num;
    }
}
