package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){//从容器中获取bean的方法
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testBeanManager(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		//测试实例化，销毁只有一次。
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}


	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}



	//声明一个属性，写上一个注解，bean就有了。这么用的
	@Autowired //意思是 spring容器将AlphaDao 注入给这个属性，这样就可以直接使用这个属性
	@Qualifier("alphaHibernate") //希望注入的不是默认的优先级，加上这个注解 给上bean的名字
	private AlphaDao alphaDao;//像这种情况，bean依赖的是接口，底层是直接不会耦合的

	@Autowired
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;


	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}



//	@Test
//	void contextLoads() {
//	}

}
