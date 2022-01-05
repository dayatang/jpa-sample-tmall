package yang.yu.test.spring;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import yang.yu.lang.InstanceProvider;
import yang.yu.lang.IocInstanceNotUniqueException;

import java.lang.annotation.Annotation;
import java.util.*;

public class SpringInstanceProvider implements InstanceProvider {

    private final ApplicationContext applicationContext;

    public SpringInstanceProvider(ApplicationContext context) {
        this.applicationContext = context;
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
            return null;
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
            return null;
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
        if (resultsWithAnnotation.isEmpty()) {
            return null;
        }
        if (resultsWithAnnotation.size() == 1) {
            return resultsWithAnnotation.get(0);
        }
        throw new IocInstanceNotUniqueException();
    }

    @Override
    public <T> Set<T> getInstances(Class<T> beanType) {
        return new HashSet<T>(applicationContext.getBeansOfType(beanType).values());
    }

}
