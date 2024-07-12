package yang.yu.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * <p>
 * 实例工厂类，充当IoC容器的门面，通过它可以获得部署在IoC容器中的Bean的实例。 InstanceFactory向客户代码隐藏了IoC
 * 工厂的具体实现。在后台，它通过InstanceProvider策略接口，允许选择不同的IoC工厂，例如Spring， Google Guice和
 * TapestryIoC等等。
 * <p>
 * IoC工厂应该在应用程序启动时装配好，也就是把初始化好的InstanceProvider实现类提供给InstanceFactory。对于web应用
 * 来说，最佳的初始化方式是创建一个Servlet过滤器或监听器，并部署到web.xml里面；对普通java应用程序来说，最佳的初始化
 * 位置是在main()函数里面；对于单元测试，最佳的初始化位置是@BeforeClass或@Before标注的方法内部。<br>
 * <p>
 * InstanceFactor顺序通过三种途径获取Bean实例。（1）如果已经给InstanceFactory设置了InstanceProvider，那么就通过后者
 * 查找Bean；（2）如果没有设置InstanceProvider，或者通过InstanceProvider无法找到Bean，就通过JDK6的ServiceLoader查找（通
 * 过在类路径或jar中的/META-INF/services/a.b.c.Abc文件中设定内容为x.y.z.Xyz，就表明类型a.b.c.Abc将通过类x.y.z.Xyz
 * 的实例提供）；（3）如果仍然没找到Bean实例，那么将返回那些通过bind()方法设置的Bean实例。（4）如果最终仍然找不到，就抛出
 * IocInstanceNotFoundException异常。
 *
 * @author yyang (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 */
public class IoC {

    private static final Logger LOGGER = LoggerFactory.getLogger(IoC.class);

    //实例提供者，代表真正的IoC容器
    private static InstanceProvider instanceProvider;

    private IoC() {
    }

    /**
     * 设置实例提供者。
     *
     * @param provider 一个实例提供者的实例。
     */
    public static void setInstanceProvider(InstanceProvider provider) {
        instanceProvider = provider;
    }

    /**
     * 根据类型获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。如果找不到该类型的实例则抛出异常。
     *
     * @param <T> 对象的类型
     * @param beanType 对象所属的类型
     * @return 类型为T的对象实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(Class<T> beanType) {
        return instanceProvider.getInstance(beanType);
    }

    /**
     * 根据类型和名称获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。不同的IoC容器用不同的方式解释beanName。
     * 具体的解释方式请参见各种InstanceProvider实现类的Javadoc。 如果找不到该类型的实例则抛出异常。
     *
     * @param <T> 类型参数
     * @param beanName bean的名称
     * @param beanType 实例的类型
     * @return 指定类型的实例。
     */
    public static <T> T getInstance(Class<T> beanType, String beanName) {
        return instanceProvider.getInstance(beanType, beanName);
    }

    /**
     * 根据类型和Annotation获取对象实例。返回的对象实例所属的类是T或它的实现类或子类。不同的IoC容器用不同的方式解释annotation。
     * 具体的解释方式请参见各种InstanceProvider实现类的Javadoc。 如果找不到该类型的实例则抛出异常。
     *
     * @param <T> 类型参数
     * @param beanType 实例的类型
     * @param annotationType 实现类的annotation类型
     * @return 指定类型的实例。
     */
    public static <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        return instanceProvider.getInstance(beanType, annotationType);
    }

    public static <T> List<T> getInstances(Class<T> beanType) {
        return instanceProvider.getInstances(beanType);
    }

    public static boolean isReady() {
        return instanceProvider != null;
    }
}