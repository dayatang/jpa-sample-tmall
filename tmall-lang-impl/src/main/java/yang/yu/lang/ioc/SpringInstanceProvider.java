package yang.yu.lang.ioc;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yang.yu.lang.InstanceProvider;
import yang.yu.lang.IocInstanceNotFoundException;
import yang.yu.lang.IocInstanceNotUniqueException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实例提供者接口的Spring实现。
 * SpringProvider内部通过Spring IoC的ApplicationContext实现对象创建。
 *
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 */
public class SpringInstanceProvider implements InstanceProvider {

    private ApplicationContext applicationContext;

    /**
     * 以一批spring配置文件的路径初始化spring实例提供者。
     *
     * @param locations spring配置文件的路径的集合。spring将从类路径开始获取这批资源文件。
     */
    public SpringInstanceProvider(String... locations) {
        applicationContext = new ClassPathXmlApplicationContext(locations);
    }

    /**
     * 从ApplicationContext生成SpringProvider
     *
     * @param applicationContext
     */
    public SpringInstanceProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 根据一批Spring配置文件初始化spring实例提供者。
     *
     * @param annotatedClasses
     */
    public SpringInstanceProvider(Class<?>... annotatedClasses) {
        applicationContext = new AnnotationConfigApplicationContext(annotatedClasses);
    }

    /**
     * 根据类型获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。如果找不到该类型的实例则返回null。
     * 如果有部署了多个类型为T的Bean则抛出NoUniqueBeanDefinitionException异常。
     *
     * @param <T>      类型参数
     * @param beanType 实例的类型
     * @return 指定类型的实例。
     */
    @Override
    public <T> T getInstance(Class<T> beanType) {
        try {
            return applicationContext.getBean(beanType);
        } catch (NoUniqueBeanDefinitionException e) {
            throw new IocInstanceNotUniqueException(e);
        } catch (NoSuchBeanDefinitionException e) {
            throw new IocInstanceNotFoundException();
        }
    }

    /**
     * 根据类型和Bean id获取对象实例。如果找不到该类型的实例则返回null。
     * @param <T>      类型参数
     * @param beanName 实现类在容器中配置的名字
     * @param beanType 实例的类型
     * @return 指定类型的实例。
     */
    @Override
    public <T> T getInstance(Class<T> beanType, String beanName) {
        try {
            return (T) applicationContext.getBean(beanName, beanType);
        } catch (NoUniqueBeanDefinitionException e) {
            throw new IocInstanceNotUniqueException(e);
        } catch (NoSuchBeanDefinitionException e) {
            throw new IocInstanceNotFoundException();
        }
    }


    /**
     * 根据类型和Annotation获取对象实例。如果找不到该类型的实例则返回null。
     * 假如有两个类MyService1和MyService2都实现了接口Service，其中MyService2标记为
     * TheAnnotation，那么getInstance(Service.class, TheAnnotation.class)将返回
     * MyService2的实例。
     *
     * @param <T>            类型参数
     * @param beanType       实例的类型
     * @param annotationType 实现类的annotation类型
     * @return 指定类型的实例。
     */
    @Override
    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            return getInstance(beanType);
        }
        Map<String, T> results = applicationContext.getBeansOfType(beanType);
        List<T> resultsWithAnnotation = new ArrayList<T>();
        for (Map.Entry<String, T> entry : results.entrySet()) {
            if (applicationContext.findAnnotationOnBean(entry.getKey(), annotationType) != null) {
                resultsWithAnnotation.add(entry.getValue());
            }
        }
        if (resultsWithAnnotation.size() == 1) {
            return resultsWithAnnotation.getFirst();
        }
        if (resultsWithAnnotation.isEmpty()) {
            throw new IocInstanceNotFoundException();
        }
        throw new IocInstanceNotUniqueException();
    }

    @Override
    public <T> List<T> getInstances(Class<T> beanType) {
        return new ArrayList<>(applicationContext.getBeansOfType(beanType).values());
    }

    @SuppressWarnings("unchecked")
    public <T> T getByBeanName(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }
}
