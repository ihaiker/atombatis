package la.renzhen.atombatis.spring.registrar;

import com.google.common.base.Splitter;
import la.renzhen.atombatis.EnableAtomBatisConfiguration;
import la.renzhen.atombatis.spring.AtomBatisMapperScanner;
import lombok.SneakyThrows;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * @author <a href="mailto:wo@renzhen.la">haiker</a>
 * @version 2018\3\25 0025 16:44
 */
public class MapperRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    Environment env;

    @SneakyThrows
    private String getClassSimpleName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return "atombatis" + Class.forName(definition.getBeanClassName()).getSimpleName();
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AtomBatisMapperScanner scanner = new AtomBatisMapperScanner(registry);
        scanner.setBeanNameGenerator(this::getClassSimpleName);
        scanner.setEnvironment(env);
        List<String> basePackages = new ArrayList<String>();

        //使用这个方式 @EnableAtomBatisConfiguration
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableAtomBatisConfiguration.class.getName()));
        if (annoAttrs != null) {
            for (String pkg : annoAttrs.getStringArray("value")) {
                if (StringUtils.hasText(pkg)) {
                    basePackages.add(pkg);
                }
            }
            for (String pkg : annoAttrs.getStringArray("basePackages")) {
                if (StringUtils.hasText(pkg)) {
                    basePackages.add(pkg);
                }
            }
        }
        //config with atombatis.scanMapper
        else {
            String scanmapers = Optional.ofNullable(env.getProperty("atombatis.scan-mapper"))
                    .orElseThrow(() -> {return new NullPointerException("not found atombatis.scan-mapper");});
            if (!StringUtils.hasText(scanmapers)) {
                throw new RuntimeException("can't found atombatis.scanMapper");
            } else {
                basePackages.addAll(Splitter.on(",").splitToList(scanmapers));
            }
        }
        scanner.scan(StringUtils.toStringArray(basePackages));
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
