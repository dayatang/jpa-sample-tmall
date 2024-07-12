package yang.yu.test.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import yang.yu.lang.IoC;

public class IoCInitiator implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringInstanceProvider instanceProvider = new SpringInstanceProvider(applicationContext);
        IoC.setInstanceProvider(instanceProvider);
    }
}
