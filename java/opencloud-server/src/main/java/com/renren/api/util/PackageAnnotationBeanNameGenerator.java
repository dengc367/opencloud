package com.renren.api.util;

import java.beans.Introspector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 用于类自动加载
 * 
 * @author Zhancheng.deng
 * @since 2011.09.05 14:35
 * 
 */
public class PackageAnnotationBeanNameGenerator extends AnnotationBeanNameGenerator {

    private static Log logger = LogFactory.getLog(PackageAnnotationBeanNameGenerator.class);

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String name = Introspector.decapitalize(definition.getBeanClassName());
        if (logger.isDebugEnabled()) {
            logger.debug("build default bean name '" + name + "' for definition " + definition);
        }
        return name;
    }

}
