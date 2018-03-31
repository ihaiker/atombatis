package la.renzhen.atombatis.spring;

import la.renzhen.atombatis.AtomBatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\25 0025 18:40
 */
public class AtomBatisMapperScanner extends ClassPathBeanDefinitionScanner {

    public AtomBatisMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public void registerDefaultFilters() {
        this.addIncludeFilter(new AnnotationTypeFilter(AtomBatisMapper.class));
        this.addIncludeFilter(new AnnotationTypeFilter(Mapper.class));
    }

    @Override
    protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
        super.postProcessBeanDefinition(beanDefinition, beanName);

        String beanClass = beanDefinition.getBeanClassName();
        beanDefinition.setBeanClassName(AtomBatisMapperFactoryBean.class.getName());
        beanDefinition.getPropertyValues().addPropertyValue("mapperInterface", beanClass);
        beanDefinition.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        beanDefinition.setPrimary(true);
    }

    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
