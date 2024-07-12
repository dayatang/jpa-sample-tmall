package yang.yu.lang.ioc;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import jakarta.inject.Named;
import yang.yu.lang.IoC;

@Named
public class IocInitiator implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        IoC.setInstanceProvider(new SpringInstanceProvider(applicationContext));
    }
}
