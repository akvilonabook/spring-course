package ru.alishev.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author Neil Alishev
 */
@Configuration
@ComponentScan("ru.alishev.springcourse")
@EnableWebMvc
@PropertySource("classpath:database.properties" )
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final Environment environment;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    /** создаем Bin jdbc template */
    @Bean
    public DataSource dataSource () {

        /** Опишем нашу базу данных для пакета -> import javax.sql.DataSource; */
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        /** опишем конфигурацию нашей базы данных, описываем наш источник данных в файле по пути -> resources.database.properties */
        dataSource.setDriverClassName( Objects.requireNonNull( environment.getProperty( "driver" ))) ;   /** название драйвера:    driver = org.postgresql.Driver */
        dataSource.setUrl( environment.getProperty( "url" ));                   /** адрес к базе данных:  url = jdbc:postgresql://localhost:5432/first_db */
        dataSource.setUsername( environment.getProperty( "user_name" ));        /** пользователь базы данных: user_name = postgres     */
        dataSource.setPassword( environment.getProperty( "password" ));         /** пароль к базе данных: password = QQqq1234     */

        return dataSource;
    }

    /** этот бин будет внедрен в класс PersonDAO.PersonDAO() при помощи @Autowired */
    @Bean
    public JdbcTemplate jdbcTemplate () {
        return new JdbcTemplate(dataSource());
    }
}
